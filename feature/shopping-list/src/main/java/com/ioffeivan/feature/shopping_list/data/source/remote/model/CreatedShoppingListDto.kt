package com.ioffeivan.feature.shopping_list.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreatedShoppingListDto(

    @SerialName("list_id")
    val id: Int,
)