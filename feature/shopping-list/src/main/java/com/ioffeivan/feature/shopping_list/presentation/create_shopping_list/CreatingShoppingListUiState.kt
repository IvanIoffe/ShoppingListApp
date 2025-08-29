package com.ioffeivan.feature.shopping_list.presentation.create_shopping_list

sealed class CreatingShoppingListUiState {
    data object Initial : CreatingShoppingListUiState()
    data object Loading : CreatingShoppingListUiState()
    data object Success : CreatingShoppingListUiState()
    data object Error : CreatingShoppingListUiState()
}