package com.ioffeivan.feature.login.presentation

data class LoginUiState(
    val key: String = "",
    val success: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
) {
    val isLoginButtonEnabled: Boolean = key.isNotEmpty() && !isLoading
}