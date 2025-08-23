package com.ioffeivan.feature.shopping_list.presentation.shopping_lists

import com.ioffeivan.feature.shopping_list.domain.model.ShoppingLists

/*sealed class ShoppingListsUiState {
    data object Loading : ShoppingListsUiState()
    data object Empty : ShoppingListsUiState()

    data class Success(
        val shoppingLists: ShoppingLists,
        val isRefreshing: Boolean,
    ) : ShoppingListsUiState()

    data class Error(
        val message: String,
    ) : ShoppingListsUiState()
}*/

data class ShoppingListsUiState(
    val shoppingLists: ShoppingLists = ShoppingLists(emptyList()),
    val isEmpty: Boolean = false,
    val isRefreshing: Boolean = false,
    val isLoading: Boolean = true,
)

/*
sealed class ShoppingListsUiState {
    data object Loading : ShoppingListsUiState()

    data class Success(
        val shoppingLists: ShoppingLists = ShoppingLists(emptyList()),
        val isEmpty: Boolean = false,
        val isRefreshing: Boolean = false,
    ) : ShoppingListsUiState()
}*/