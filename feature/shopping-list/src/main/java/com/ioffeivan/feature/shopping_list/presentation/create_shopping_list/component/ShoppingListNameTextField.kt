package com.ioffeivan.feature.shopping_list.presentation.create_shopping_list.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ioffeivan.core.designsystem.component.SlaTextField

@Composable
fun ShoppingListNameTextField(
    shoppingListName: String,
    onShoppingListNameChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    SlaTextField(
        value = shoppingListName,
        onValueChange = onShoppingListNameChange,
        modifier = modifier,
        label = {
            Text(
                text = "List name",
                style = MaterialTheme.typography.bodyLarge,
            )
        },
        placeholder = {
            Text(
                text = "Enter list name",
                style = MaterialTheme.typography.bodyLarge,
            )
        },
        singleLine = true,
    )
}