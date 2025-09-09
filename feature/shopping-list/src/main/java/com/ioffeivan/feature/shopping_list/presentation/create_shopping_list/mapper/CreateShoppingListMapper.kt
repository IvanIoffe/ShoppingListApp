package com.ioffeivan.feature.shopping_list.presentation.create_shopping_list.mapper

import com.ioffeivan.feature.shopping_list.domain.model.CreateShoppingList
import com.ioffeivan.feature.shopping_list.presentation.create_shopping_list.EnteringShoppingListInfoUiState

fun EnteringShoppingListInfoUiState.toCreateShoppingList(): CreateShoppingList {
    return CreateShoppingList(
        name = name.trim(),
    )
}