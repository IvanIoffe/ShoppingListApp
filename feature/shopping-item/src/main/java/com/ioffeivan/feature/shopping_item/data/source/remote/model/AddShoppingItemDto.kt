package com.ioffeivan.feature.shopping_item.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddShoppingItemDto(

    @SerialName("value")
    val name: String,

    @SerialName("n")
    val quantity: Int,

    @SerialName("id")
    val listId: Int,
)