package com.ioffeivan.feature.shopping_item.domain.usecase

import com.ioffeivan.feature.shopping_item.domain.repository.ShoppingItemRepository
import javax.inject.Inject

class DeleteShoppingItemFromShoppingListUseCase @Inject constructor(
    private val shoppingItemRepository: ShoppingItemRepository,
) {
    suspend operator fun invoke(id: Int) {
        shoppingItemRepository.deleteShoppingItem(id)
    }
}