package com.ioffeivan.feature.login.presentation.mapper

import com.ioffeivan.feature.login.domain.model.LoginCredentials
import com.ioffeivan.feature.login.presentation.LoginUiState

fun LoginUiState.toLoginCredentials(): LoginCredentials {
    return LoginCredentials(
        authKey = authKey,
    )
}