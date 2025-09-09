package com.ioffeivan.core.designsystem.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import com.ioffeivan.core.designsystem.R

object SlaIcons {
    val Add = IconSpec.Vector(Icons.Rounded.Add)
    val ArrowBack = IconSpec.Vector(Icons.AutoMirrored.Rounded.ArrowBack)
    val Close = IconSpec.Vector(Icons.Rounded.Close)

    val Delete = IconSpec.Res(R.drawable.ic_delete)
    val Key = IconSpec.Res(R.drawable.ic_key)
    val ShoppingCartOff = IconSpec.Res(R.drawable.ic_shopping_cart_off)
}