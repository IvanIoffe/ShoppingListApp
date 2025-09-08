package com.ioffeivan.feature.shopping_item.presentation.add_shopping_item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ioffeivan.core.common.Result
import com.ioffeivan.feature.shopping_item.domain.model.AddShoppingItem
import com.ioffeivan.feature.shopping_item.domain.usecase.AddShoppingItemToShoppingListUseCase
import com.ioffeivan.feature.shopping_item.presentation.add_shopping_item.mapper.toAddShoppingItem
import dagger.Lazy
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
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

@HiltViewModel(assistedFactory = AddShoppingItemViewModel.Factory::class)
class AddShoppingItemViewModel @AssistedInject constructor(
    private val addShoppingItemUseCase: Lazy<AddShoppingItemToShoppingListUseCase>,
    @Assisted val listId: Int,
) : ViewModel() {

    private val _addShoppingItemEvent = Channel<AddShoppingItemEvent>()
    val addShoppingItemEvent = _addShoppingItemEvent.receiveAsFlow()

    private val addShoppingItemTrigger = MutableSharedFlow<AddShoppingItem>()

    private val _enteringShoppingItemInfoUiState =
        MutableStateFlow(EnteringShoppingItemInfoUiState())
    val enteringShoppingItemInfoUiState get() = _enteringShoppingItemInfoUiState.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val addShoppingItemUiState = addShoppingItemTrigger
        .flatMapLatest { addShoppingItem ->
            addShoppingItemUseCase.get().invoke(addShoppingItem)
                .onEach {
                    when (it) {
                        is Result.Error -> {
                            _addShoppingItemEvent.send(AddShoppingItemEvent.ShowSnackbar(it.message))
                        }

                        is Result.Success -> {
                            _addShoppingItemEvent.send(AddShoppingItemEvent.NavigateToBack)
                        }

                        else -> {}
                    }
                }
                .map { result ->
                    when (result) {
                        Result.Loading -> AddShoppingItemUiState.Loading
                        else -> AddShoppingItemUiState.Initial
                    }
                }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000L),
            AddShoppingItemUiState.Initial,
        )

    fun onShoppingItemNameChange(name: String) {
        _enteringShoppingItemInfoUiState.update {
            it.copy(name = name)
        }
    }

    fun onShoppingItemQuantityChange(quantity: String) {
        if (isValidQuantity(quantity)) {
            _enteringShoppingItemInfoUiState.update {
                it.copy(quantity = quantity)
            }
        }
    }

    fun onAddShoppingItem() {
        viewModelScope.launch {
            addShoppingItemTrigger.emit(
                _enteringShoppingItemInfoUiState.value.toAddShoppingItem(listId)
            )
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(
            listId: Int,
        ): AddShoppingItemViewModel
    }
}

sealed interface AddShoppingItemEvent {
    data class ShowSnackbar(val message: String) : AddShoppingItemEvent
    data object NavigateToBack : AddShoppingItemEvent
}