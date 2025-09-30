package com.ioffeivan.feature.shopping_item.presentation.add_shopping_item.mapper

import com.ioffeivan.feature.shopping_item.domain.model.ShoppingItem
import com.ioffeivan.feature.shopping_item.presentation.add_shopping_item.EnteringShoppingItemInfoUiState

fun EnteringShoppingItemInfoUiState.toShoppingItem(
    listId: Int,
): ShoppingItem {
    return ShoppingItem(
        name = name.trim(),
        quantity = quantity.trim(),
        listId = listId,
    )
}