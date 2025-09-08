package com.ioffeivan.feature.shopping_item.domain.usecase

import com.ioffeivan.core.common.Result
import com.ioffeivan.feature.shopping_item.domain.model.ShoppingItems
import com.ioffeivan.feature.shopping_item.domain.repository.ShoppingItemRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveShoppingItemsUseCase @Inject constructor(
    private val shoppingItemRepository: ShoppingItemRepository,
) {
    operator fun invoke(listId: Int): Flow<Result<ShoppingItems>> {
        return shoppingItemRepository.observeShoppingItems(listId)
    }
}