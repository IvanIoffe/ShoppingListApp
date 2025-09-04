package com.ioffeivan.feature.shopping_item.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.ioffeivan.feature.shopping_item.presentation.shopping_items.ShoppingItemRoute
import com.ioffeivan.feature.shopping_item.presentation.shopping_items.ShoppingItemsViewModel
import kotlinx.serialization.Serializable

@Serializable
data class ShoppingItemBaseRoute(val listId: Int, val listName: String)

@Serializable
data object ShoppingItemsRoute

fun NavController.navigateToShoppingItem(
    listId: Int,
    listName: String,
    navOptions: NavOptions? = null,
) {
    navigate(
        route = ShoppingItemBaseRoute(listId = listId, listName = listName),
        navOptions = navOptions,
    )
}

fun NavGraphBuilder.shoppingItem(
    entry: () -> NavBackStackEntry,
    onBack: () -> Unit,
) {
    navigation<ShoppingItemBaseRoute>(
        startDestination = ShoppingItemsRoute,
    ) {
        composable<ShoppingItemsRoute>(
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
            val parentEntry = remember { entry() }
            val (listId, listName) = parentEntry.toRoute<ShoppingItemBaseRoute>()

            ShoppingItemRoute(
                onBack = onBack,
                viewModel = hiltViewModel<ShoppingItemsViewModel, ShoppingItemsViewModel.Factory>(
                    key = "$listId|$listName"
                ) { factory ->
                    factory.create(listId = listId, listName = listName)
                },
            )
        }
    }
}