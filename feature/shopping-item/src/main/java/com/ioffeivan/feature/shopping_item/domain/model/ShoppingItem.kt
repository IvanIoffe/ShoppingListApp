package com.ioffeivan.feature.shopping_item.domain.model

data class ShoppingItem(
    val id: Int = 0,
    val name: String,
    val quantity: String,
    val listId: Int,
)