package com.ioffeivan.feature.shopping_item.presentation.shopping_items.utils

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.ioffeivan.feature.shopping_item.domain.model.ShoppingItem
import com.ioffeivan.feature.shopping_item.domain.model.ShoppingItems

class ShoppingItemsPreviewParameterProvider : PreviewParameterProvider<ShoppingItems> {
    override val values: Sequence<ShoppingItems>
        get() = sequenceOf(
            ShoppingItems(
                listOf(
                    ShoppingItem(
                        id = 1,
                        name = "Tomatoes",
                        quantity = "1.7 kg"
                    ),
                    ShoppingItem(
                        id = 2,
                        name = "Apples",
                        quantity = "4 pcs"
                    ),
                    ShoppingItem(
                        id = 3,
                        name = "Coffee",
                        quantity = "250 g"
                    ),
                    ShoppingItem(
                        id = 4,
                        name = "Cheese",
                        quantity = "300 g"
                    ),
                    ShoppingItem(
                        id = 5,
                        name = "Bananas",
                        quantity = "1 bunch"
                    ),
                    ShoppingItem(
                        id = 6,
                        name = "Milk",
                        quantity = "500 ml"
                    ),
                    ShoppingItem(
                        id = 7,
                        name = "Eggs",
                        quantity = "12 pcs"
                    ),
                    ShoppingItem(
                        id = 8,
                        name = "Butter",
                        quantity = "200 g"
                    ),
                    ShoppingItem(
                        id = 9,
                        name = "Yogurt",
                        quantity = "1500 ml"
                    ),
                    ShoppingItem(
                        id = 10,
                        name = "Olive oil",
                        quantity = "500 ml"
                    ),
                    ShoppingItem(
                        id = 11,
                        name = "Spaghetti",
                        quantity = "500 g"
                    ),
                    ShoppingItem(
                        id = 12,
                        name = "Cucumbers",
                        quantity = "0.6 kg"
                    ),
                    ShoppingItem(
                        id = 13,
                        name = "Potatoes",
                        quantity = "1.1 kg"
                    ),
                    ShoppingItem(
                        id = 14,
                        name = "Bread",
                        quantity = "1 loaf"
                    ),
                    ShoppingItem(
                        id = 15,
                        name = "Chicken breast",
                        quantity = "400 g"
                    ),
                )
            )
        )
}