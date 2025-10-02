package com.ioffeivan.feature.shopping_item.domain.usecase

import com.ioffeivan.feature.shopping_item.domain.model.ShoppingItem
import com.ioffeivan.feature.shopping_item.domain.repository.ShoppingItemRepository
import javax.inject.Inject

class AddShoppingItemToShoppingListUseCase @Inject constructor(
    private val shoppingItemRepository: ShoppingItemRepository,
) {
    suspend operator fun invoke(shoppingItem: ShoppingItem) {
        shoppingItemRepository.addShoppingItem(shoppingItem)
    }
}