package com.ioffeivan.feature.shopping_list.presentation.create_shopping_list

data class EnteringShoppingListDataUiState(
    val shoppingListName: String = "",
) {
    val createShoppingListButtonEnabled = shoppingListName.isNotBlank()
}