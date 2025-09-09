package com.ioffeivan.shoppinglistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.ioffeivan.core.designsystem.theme.ShoppingListAppTheme
import com.ioffeivan.core.ui.ObserveAsEventsWithLifecycle
import com.ioffeivan.feature.login.presentation.navigation.navigateToLogin
import com.ioffeivan.feature.shopping_list.presentation.navigation.navigateToShoppingList
import com.ioffeivan.shoppinglistapp.navigation.AppNavGraph
import com.ioffeivan.shoppinglistapp.navigation.SplashRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ShoppingListAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()

                    ObserveAsEventsWithLifecycle(
                        events = mainActivityViewModel.events,
                        onEvent = { event ->
                            when (event) {
                                MainActivityEvent.NavigateToLogin -> {
                                    navController.navigateToLogin(
                                        navOptions {
                                            popUpTo(SplashRoute) { inclusive = true }
                                        }
                                    )
                                }

                                MainActivityEvent.NavigateToShoppingLists -> {
                                    navController.navigateToShoppingList(
                                        navOptions {
                                            popUpTo(SplashRoute) { inclusive = true }
                                        }
                                    )
                                }
                            }
                        }
                    )

                    AppNavGraph(
                        navController = navController,
                    )
                }
            }
        }
    }
}