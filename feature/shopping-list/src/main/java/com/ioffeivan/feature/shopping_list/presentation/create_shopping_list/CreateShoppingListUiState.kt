package com.ioffeivan.feature.shopping_list.presentation.create_shopping_list

sealed class CreateShoppingListUiState {
    data object Initial : CreateShoppingListUiState()
    data object Loading : CreateShoppingListUiState()
    data object Success : CreateShoppingListUiState()
    data object Error : CreateShoppingListUiState()
}