package com.ioffeivan.shoppinglistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ioffeivan.core.designsystem.theme.ShoppingListAppTheme
import com.ioffeivan.feature.login.presentation.LoginRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ShoppingListAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LoginRoute(
                        onLoginSuccess = {},
                    )
                }
            }
        }
    }
}