package com.ioffeivan.feature.shopping_item.presentation.add_shopping_item

data class EnteringShoppingItemInfoUiState(
    val name: String = "",
    val quantity: String = "",
) {
    val addShoppingItemButtonEnabled = name.isNotBlank() && quantity.isNotBlank()
}

fun isValidQuantity(quantity: String): Boolean = quantity.all { it.isDigit() }