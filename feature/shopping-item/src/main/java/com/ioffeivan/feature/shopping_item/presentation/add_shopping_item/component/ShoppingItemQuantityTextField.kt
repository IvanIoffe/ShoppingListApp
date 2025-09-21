package com.ioffeivan.feature.shopping_item.presentation.add_shopping_item.component

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ioffeivan.core.designsystem.component.PrimaryTextField
import com.ioffeivan.feature.shopping_item.R

@Composable
fun ShoppingItemQuantityTextField(
    shoppingItemQuantity: String,
    onShoppingItemQuantityChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    PrimaryTextField(
        value = shoppingItemQuantity,
        onValueChange = onShoppingItemQuantityChange,
        modifier = modifier,
        label = {
            Text(
                text = stringResource(R.string.shopping_item_quantity),
                style = MaterialTheme.typography.bodyLarge,
            )
        },
        placeholder = {
            Text(
                text = stringResource(R.string.enter_shopping_item_quantity),
                style = MaterialTheme.typography.bodyLarge,
            )
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = true,
    )
}