package com.ioffeivan.feature.shopping_list.presentation.shopping_lists.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ioffeivan.core.designsystem.preview.PreviewContainer
import com.ioffeivan.feature.shopping_list.R
import com.ioffeivan.feature.shopping_list.domain.model.ShoppingList
import com.ioffeivan.feature.shopping_list.presentation.shopping_lists.ShoppingListsColors

@Composable
fun ShoppingListItem(
    shoppingList: ShoppingList,
    modifier: Modifier = Modifier,
    onShoppingItemClick: () -> Unit,
    onDeleteClick: (Int) -> Unit,
) {
    Box(modifier = modifier) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onShoppingItemClick)
                .padding(start = 16.dp, top = 8.dp, end = 8.dp, bottom = 8.dp),
        ) {
            Text(
                text = shoppingList.name,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                ),
            )

            IconButton(onClick = { onDeleteClick(shoppingList.id) }) {
                Icon(
                    painter = painterResource(R.drawable.ic_delete),
                    contentDescription = null,
                    tint = ShoppingListsColors.deleteIconButtonColor,
                    modifier = Modifier
                        .size(28.dp),
                )
            }
        }
    }
}

@Preview
@Composable
fun ShoppingListItemPreviewLight() {
    PreviewContainer {
        ShoppingListItem(
            shoppingList = ShoppingList(0, "Name List"),
            onShoppingItemClick = {},
            onDeleteClick = {},
        )
    }
}

@Preview
@Composable
fun ShoppingListItemPreviewDark() {
    PreviewContainer(darkTheme = true) {
        ShoppingListItem(
            shoppingList = ShoppingList(0, "Name List"),
            onShoppingItemClick = {},
            onDeleteClick = {},
        )
    }
}