package com.ioffeivan.feature.shopping_list.presentation.create_shopping_list.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ioffeivan.core.designsystem.component.SlaTextField
import com.ioffeivan.feature.shopping_list.R

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
                text = stringResource(R.string.shopping_list_name),
                style = MaterialTheme.typography.bodyLarge,
            )
        },
        placeholder = {
            Text(
                text = stringResource(R.string.enter_shopping_list_name),
                style = MaterialTheme.typography.bodyLarge,
            )
        },
        singleLine = true,
    )
}