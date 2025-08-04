package com.ioffeivan.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Error(

    @SerialName("error")
    val message: String,
)