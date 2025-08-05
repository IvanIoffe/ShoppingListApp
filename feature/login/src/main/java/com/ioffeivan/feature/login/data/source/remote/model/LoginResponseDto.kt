package com.ioffeivan.feature.login.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(

    @SerialName("success")
    val success: Boolean,

    @SerialName("error")
    val errorMessage: String? = null,
)