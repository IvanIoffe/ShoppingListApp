package com.ioffeivan.feature.shopping_item.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateShoppingItemDto(

    @SerialName("id")
    val listId: Int,

    @SerialName("value")
    val name: String,

    @SerialName("n")
    val quantity: String,
)