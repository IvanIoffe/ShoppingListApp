package com.ioffeivan.feature.shopping_item.domain.usecase

import com.ioffeivan.feature.shopping_item.domain.repository.ShoppingItemRepository
import javax.inject.Inject

class RefreshShoppingItemsUseCase @Inject constructor(
    private val shoppingItemRepository: ShoppingItemRepository,
) {
    suspend operator fun invoke(listLocalId: Int, listServerId: Int) {
        return shoppingItemRepository.refreshShoppingItems(
            listLocalId = listLocalId,
            listServerId = listServerId,
        )
    }
}