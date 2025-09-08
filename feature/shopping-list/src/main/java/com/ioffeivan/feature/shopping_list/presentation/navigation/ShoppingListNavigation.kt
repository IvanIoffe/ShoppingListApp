package com.ioffeivan.feature.shopping_list.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ioffeivan.feature.shopping_list.domain.model.ShoppingList
import com.ioffeivan.feature.shopping_list.presentation.create_shopping_list.CreateShoppingListRoute
import com.ioffeivan.feature.shopping_list.presentation.shopping_lists.ShoppingListsRoute
import kotlinx.serialization.Serializable

@Serializable
data object ShoppingListBaseRoute

@Serializable
data object ShoppingListsRoute

@Serializable
data object CreateShoppingListRoute

fun NavController.navigateToShoppingList(navOptions: NavOptions? = null) =
    navigate(route = ShoppingListBaseRoute, navOptions = navOptions)

fun NavController.navigateToCreateShoppingList(navOptions: NavOptions? = null) =
    navigate(route = CreateShoppingListRoute, navOptions = navOptions)

fun NavGraphBuilder.shoppingList(
    onShoppingListClick: (ShoppingList) -> Unit,
    onCreateShoppingListClick: () -> Unit,
    onBack: () -> Unit,
) {
    navigation<ShoppingListBaseRoute>(startDestination = ShoppingListsRoute) {
        composable<ShoppingListsRoute> {
            ShoppingListsRoute(
                onShoppingListClick = onShoppingListClick,
                onCreateShoppingListClick = onCreateShoppingListClick,
            )
        }

        composable<CreateShoppingListRoute>(
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700),
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
        ) {
            CreateShoppingListRoute(
                onBack = onBack,
            )
        }
    }
}