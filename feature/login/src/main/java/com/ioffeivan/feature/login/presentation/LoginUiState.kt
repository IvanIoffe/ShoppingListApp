package com.ioffeivan.feature.login.presentation

data class LoginUiState(
    val authKey: String = "",
    val success: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
) {
    val isLoginButtonEnabled: Boolean = authKey.isNotEmpty() && !isLoading
}