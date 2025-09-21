package com.ioffeivan.core.designsystem.component

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ioffeivan.core.designsystem.icon.PrimaryIcon
import com.ioffeivan.core.designsystem.icon.PrimaryIcons

@Composable
fun AddFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        shape = CircleShape,
    ) {
        PrimaryIcon(icon = PrimaryIcons.Add)
    }
}