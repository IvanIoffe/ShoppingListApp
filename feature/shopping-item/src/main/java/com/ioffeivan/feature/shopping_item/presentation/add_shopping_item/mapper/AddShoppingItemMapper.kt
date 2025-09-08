package com.ioffeivan.feature.shopping_item.presentation.add_shopping_item.mapper

import com.ioffeivan.feature.shopping_item.domain.model.AddShoppingItem
import com.ioffeivan.feature.shopping_item.presentation.add_shopping_item.EnteringShoppingItemInfoUiState

fun EnteringShoppingItemInfoUiState.toAddShoppingItem(
    listId: Int,
): AddShoppingItem {
    return AddShoppingItem(
        listId = listId,
        name = name.trim(),
        quantity = quantity.trim().toIntOrNull() ?: 0,
    )
}