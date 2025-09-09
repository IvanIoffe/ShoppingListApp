package com.ioffeivan.shoppinglistapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.ioffeivan.shoppinglistapp.navigation.AppNavGraph

@Composable
fun AppScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        AppNavGraph(
            navController = navController,
            modifier = modifier,
        )
    }
}