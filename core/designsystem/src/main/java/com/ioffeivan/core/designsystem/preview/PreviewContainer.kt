package com.ioffeivan.core.designsystem.preview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ioffeivan.core.designsystem.theme.ShoppingListAppTheme

@Composable
fun PreviewContainer(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit,
) {
    ShoppingListAppTheme(darkTheme = darkTheme) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            content = content,
        )
    }
}