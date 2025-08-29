package com.ioffeivan.feature.shopping_list.presentation.create_shopping_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ioffeivan.core.common.Result
import com.ioffeivan.feature.shopping_list.domain.model.CreateShoppingList
import com.ioffeivan.feature.shopping_list.domain.usecase.CreateShoppingListUseCase
import com.ioffeivan.feature.shopping_list.presentation.create_shopping_list.mapper.toCreateShoppingList
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateShoppingListViewModel @Inject constructor(
    private val createShoppingListUseCase: Lazy<CreateShoppingListUseCase>,
) : ViewModel() {

    private val createShoppingListEventChannel = Channel<CreateShoppingListEvent>()
    val createShoppingListEvent = createShoppingListEventChannel.receiveAsFlow()

    private val _createTrigger = MutableSharedFlow<CreateShoppingList>()

    private val _enteringShoppingListDataUiState =
        MutableStateFlow(EnteringShoppingListDataUiState())
    val enteringShoppingListDataUiState = _enteringShoppingListDataUiState.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val creatingShoppingListUiState = _createTrigger
        .flatMapLatest { createShoppingList ->
            createShoppingListUseCase.get().invoke(createShoppingList)
                .map { result ->
                    when (result) {
                        is Result.Success -> {
                            createShoppingListEventChannel.send(CreateShoppingListEvent.NavigateToBack)
                            CreatingShoppingListUiState.Success
                        }

                        Result.Loading -> CreatingShoppingListUiState.Loading

                        is Result.Error -> {
                            createShoppingListEventChannel.send(
                                CreateShoppingListEvent.ShowSnackbar(message = result.message)
                            )
                            CreatingShoppingListUiState.Error
                        }
                    }
                }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000L),
            CreatingShoppingListUiState.Initial,
        )

    fun onShoppingListNameChange(shoppingListName: String) {
        _enteringShoppingListDataUiState.update {
            it.copy(shoppingListName = shoppingListName)
        }
    }

    fun onCreateShoppingListClick() {
        viewModelScope.launch {
            _createTrigger.emit(_enteringShoppingListDataUiState.value.toCreateShoppingList())
        }
    }
}

sealed interface CreateShoppingListEvent {
    data class ShowSnackbar(val message: String) : CreateShoppingListEvent
    data object NavigateToBack : CreateShoppingListEvent
}