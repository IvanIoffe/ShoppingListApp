package com.ioffeivan.feature.login.presentation

data class LoginUiState(
    val authKey: String = "",
    val isLoading: Boolean = false,
) {
    val isLoginButtonEnabled: Boolean
        get() = authKey.isNotBlank() && !isLoading
}