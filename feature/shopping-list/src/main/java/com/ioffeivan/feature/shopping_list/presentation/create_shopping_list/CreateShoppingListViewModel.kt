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
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateShoppingListViewModel @Inject constructor(
    private val createShoppingListUseCase: Lazy<CreateShoppingListUseCase>,
) : ViewModel() {

    private val _createShoppingListEvent = Channel<CreateShoppingListEvent>()
    val createShoppingListEvent = _createShoppingListEvent.receiveAsFlow()

    private val _createShoppingListTrigger = MutableSharedFlow<CreateShoppingList>()

    private val _enteringShoppingListInfoUiState =
        MutableStateFlow(EnteringShoppingListInfoUiState())
    val enteringShoppingListInfoUiState = _enteringShoppingListInfoUiState.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val createShoppingListUiState = _createShoppingListTrigger
        .flatMapLatest { createShoppingList ->
            createShoppingListUseCase.get().invoke(createShoppingList)
                .onEach {
                    when (it) {
                        is Result.Error -> {
                            _createShoppingListEvent.send(CreateShoppingListEvent.ShowSnackbar(it.message))
                        }

                        is Result.Success -> {
                            _createShoppingListEvent.send(CreateShoppingListEvent.NavigateToBack)
                        }

                        else -> {}
                    }
                }
                .map { result ->
                    when (result) {
                        is Result.Success -> CreateShoppingListUiState.Success
                        Result.Loading -> CreateShoppingListUiState.Loading
                        is Result.Error -> CreateShoppingListUiState.Error
                    }
                }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000L),
            CreateShoppingListUiState.Initial,
        )

    fun onShoppingListNameChange(shoppingListName: String) {
        _enteringShoppingListInfoUiState.update {
            it.copy(name = shoppingListName)
        }
    }

    fun onCreateShoppingListClick() {
        viewModelScope.launch {
            _createShoppingListTrigger.emit(_enteringShoppingListInfoUiState.value.toCreateShoppingList())
        }
    }
}

sealed interface CreateShoppingListEvent {
    data class ShowSnackbar(val message: String) : CreateShoppingListEvent
    data object NavigateToBack : CreateShoppingListEvent
}