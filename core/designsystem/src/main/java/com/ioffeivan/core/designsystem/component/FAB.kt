package com.ioffeivan.core.designsystem.component

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ioffeivan.core.designsystem.icon.SlaIcon
import com.ioffeivan.core.designsystem.icon.SlaIcons

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
        SlaIcon(icon = SlaIcons.Add)
    }
}