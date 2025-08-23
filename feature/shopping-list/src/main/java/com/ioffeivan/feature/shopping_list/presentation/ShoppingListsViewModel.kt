package com.ioffeivan.feature.shopping_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ioffeivan.core.common.Result
import com.ioffeivan.feature.shopping_list.domain.usecase.ObserveShoppingListsUseCase
import com.ioffeivan.feature.shopping_list.domain.usecase.RefreshShoppingListsUseCase
import com.ioffeivan.feature.shopping_list.presentation.shopping_lists.ShoppingListsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingListsViewModel @Inject constructor(
    observeShoppingListsUseCase: ObserveShoppingListsUseCase,
    private val refreshShoppingListsUseCase: RefreshShoppingListsUseCase,
) : ViewModel() {

    private val _oneTimeEvent = Channel<OneTimeEvent>()
    val oneTimeEvent = _oneTimeEvent.receiveAsFlow()

    private val _shoppingListsUiState = MutableStateFlow(ShoppingListsUiState())

    val shoppingListsUiState = observeShoppingListsUseCase()
        .drop(1)
        .map { result ->
            when (result) {
                is Result.Success -> {
                    _shoppingListsUiState.updateAndGet {
                        it.copy(
                            shoppingLists = result.data,
                            isEmpty = result.data.items.isEmpty(),
                            isRefreshing = false,
                            isLoading = false,
                        )
                    }
                }

                is Result.Error -> {
                    _oneTimeEvent.send(OneTimeEvent.ShowErrorSnackbar(result.message))

                    _shoppingListsUiState.updateAndGet {
                        it.copy(
                            isRefreshing = false,
                            isLoading = false,
                        )
                    }
                }

                Result.Loading -> {
                    _shoppingListsUiState.updateAndGet {
                        it.copy(isRefreshing = true)
                    }
                }
            }
        }.onStart {
            refreshShoppingListsUseCase()
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000L),
            ShoppingListsUiState(),
        )

    fun refreshShoppingLists() {
        viewModelScope.launch(Dispatchers.IO) {
            refreshShoppingListsUseCase()
        }
    }

    fun createShoppingList() {

    }

    fun deleteShoppingList(id: Int) {

    }
}

sealed interface OneTimeEvent {
    data class ShowErrorSnackbar(val message: String) : OneTimeEvent
}