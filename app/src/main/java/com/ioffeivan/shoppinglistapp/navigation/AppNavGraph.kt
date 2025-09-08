package com.ioffeivan.shoppinglistapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.ioffeivan.feature.login.presentation.navigation.LoginRoute
import com.ioffeivan.feature.login.presentation.navigation.loginScreen
import com.ioffeivan.feature.shopping_item.presentation.navigation.ShoppingItemBaseRoute
import com.ioffeivan.feature.shopping_item.presentation.navigation.navigateToAddShoppingItem
import com.ioffeivan.feature.shopping_item.presentation.navigation.navigateToShoppingItem
import com.ioffeivan.feature.shopping_item.presentation.navigation.shoppingItem
import com.ioffeivan.feature.shopping_list.presentation.navigation.navigateToCreateShoppingList
import com.ioffeivan.feature.shopping_list.presentation.navigation.navigateToShoppingList
import com.ioffeivan.feature.shopping_list.presentation.navigation.shoppingList
import kotlinx.serialization.Serializable

@Serializable
object SplashRoute

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = SplashRoute,
        modifier = modifier,
    ) {
        composable<SplashRoute> {}

        loginScreen(
            onLoginSuccess = {
                navController.navigateToShoppingList(
                    navOptions = navOptions {
                        popUpTo(LoginRoute) { inclusive = true }
                    }
                )
            }
        )

        shoppingList(
            onShoppingListClick = { shoppingList ->
                navController.navigateToShoppingItem(
                    listId = shoppingList.id,
                    listName = shoppingList.name,
                )
            },
            onCreateShoppingListClick = navController::navigateToCreateShoppingList,
            onBack = navController::popBackStack,
        )

        shoppingItem(
            navBackStackEntry = { navController.getBackStackEntry<ShoppingItemBaseRoute>() },
            onBack = navController::popBackStack,
            onAddShoppingItemClick = navController::navigateToAddShoppingItem
        )
    }
}