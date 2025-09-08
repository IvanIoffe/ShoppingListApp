package com.ioffeivan.feature.shopping_item.domain.model

data class AddShoppingItem(
    val name: String,
    val quantity: Int,
    val listId: Int,
)