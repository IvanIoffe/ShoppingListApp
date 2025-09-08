package com.ioffeivan.feature.shopping_item.presentation.add_shopping_item

sealed class AddShoppingItemUiState {
    data object Initial : AddShoppingItemUiState()
    data object Loading : AddShoppingItemUiState()
}