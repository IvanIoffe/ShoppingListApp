package com.ioffeivan.shoppinglistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.ioffeivan.core.designsystem.theme.ShoppingListAppTheme
import com.ioffeivan.shoppinglistapp.ui.AppScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ShoppingListAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val uiState by mainActivityViewModel.uiState.collectAsStateWithLifecycle()

                    if (uiState !is MainActivityUiState.Loading) {
                        val navController = rememberNavController()

                        AppScreen(
                            navController = navController,
                            isLoggedIn = uiState is MainActivityUiState.LoggedIn,
                        )
                    }
                }
            }
        }
    }
}