package com.ioffeivan.feature.login.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ioffeivan.feature.login.presentation.LoginRoute
import kotlinx.serialization.Serializable

@Serializable
data object LoginRoute

fun NavController.navigateToLogin(navOptions: NavOptions? = null) =
    navigate(route = LoginRoute, navOptions = navOptions)

fun NavGraphBuilder.loginScreen(
    onLoginSuccess: () -> Unit,
) {
    composable<LoginRoute> {
        LoginRoute(
            onLoginSuccess = onLoginSuccess,
        )
    }
}
