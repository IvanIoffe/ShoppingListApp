package com.ioffeivan.core.designsystem.icon

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource

sealed class IconSpec {
    data class Vector(val imageVector: ImageVector) : IconSpec()
    data class Res(@DrawableRes val resId: Int) : IconSpec()
}

@Composable
fun SlaIcon(
    icon: IconSpec,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    tint: Color = LocalContentColor.current,
) {
    val imageVector = when (icon) {
        is IconSpec.Vector -> icon.imageVector
        is IconSpec.Res -> ImageVector.vectorResource(icon.resId)
    }

    Icon(
        imageVector = imageVector,
        modifier = modifier,
        contentDescription = contentDescription,
        tint = tint,
    )
}