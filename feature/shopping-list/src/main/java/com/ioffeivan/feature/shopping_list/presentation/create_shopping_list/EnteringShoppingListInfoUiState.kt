package com.ioffeivan.feature.shopping_list.presentation.create_shopping_list

data class EnteringShoppingListInfoUiState(
    val name: String = "",
) {
    val createShoppingListButtonEnabled = name.isNotBlank()
}