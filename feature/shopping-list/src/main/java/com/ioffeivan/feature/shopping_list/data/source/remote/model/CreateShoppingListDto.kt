package com.ioffeivan.feature.shopping_list.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateShoppingListDto(

    @SerialName("name")
    val name: String,
)