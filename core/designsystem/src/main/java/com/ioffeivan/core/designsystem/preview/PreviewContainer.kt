package com.ioffeivan.core.designsystem.preview

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.ioffeivan.core.designsystem.theme.ShoppingListAppTheme

@Composable
fun PreviewContainer(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit,
) {
    ShoppingListAppTheme(darkTheme = darkTheme) {
        Surface(
            content = content,
        )
    }
}