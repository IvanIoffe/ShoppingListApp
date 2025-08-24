package com.ioffeivan.feature.shopping_list.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ioffeivan.feature.shopping_list.presentation.shopping_lists.ShoppingListsRoute
import kotlinx.serialization.Serializable

@Serializable
data object ShoppingListBaseRoute

@Serializable
data object ShoppingListsRoute

fun NavGraphBuilder.shoppingList() {

    navigation<ShoppingListBaseRoute>(startDestination = ShoppingListsRoute) {
        composable<ShoppingListsRoute> {
            ShoppingListsRoute()
        }
    }
}