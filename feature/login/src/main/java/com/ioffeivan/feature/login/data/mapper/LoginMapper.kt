package com.ioffeivan.feature.login.data.mapper

import com.ioffeivan.feature.login.data.source.remote.model.LoginCredentialsDto
import com.ioffeivan.feature.login.domain.model.LoginCredentials

fun LoginCredentials.toDto(): LoginCredentialsDto {
    return LoginCredentialsDto(
        authKey = authKey,
    )
}