package com.ioffeivan.feature.shopping_list.presentation.shopping_lists

import com.ioffeivan.feature.shopping_list.domain.model.ShoppingLists

data class ShoppingListsUiState(
    val shoppingLists: ShoppingLists = ShoppingLists(emptyList()),
    val isEmpty: Boolean = false,
    val isLoading: Boolean = true,
)