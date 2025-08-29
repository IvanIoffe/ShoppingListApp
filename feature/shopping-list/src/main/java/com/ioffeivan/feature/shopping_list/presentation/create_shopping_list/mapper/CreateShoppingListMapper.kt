package com.ioffeivan.feature.shopping_list.presentation.create_shopping_list.mapper

import com.ioffeivan.feature.shopping_list.domain.model.CreateShoppingList
import com.ioffeivan.feature.shopping_list.presentation.create_shopping_list.EnteringShoppingListDataUiState

fun EnteringShoppingListDataUiState.toCreateShoppingList(): CreateShoppingList {
    return CreateShoppingList(
        name = shoppingListName.trim(),
    )
}