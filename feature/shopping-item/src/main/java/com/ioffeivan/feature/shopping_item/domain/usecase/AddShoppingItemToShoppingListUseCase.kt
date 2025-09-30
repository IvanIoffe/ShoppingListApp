package com.ioffeivan.feature.shopping_item.domain.usecase

import com.ioffeivan.core.common.Result
import com.ioffeivan.feature.shopping_item.domain.model.ShoppingItem
import com.ioffeivan.feature.shopping_item.domain.repository.ShoppingItemRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddShoppingItemToShoppingListUseCase @Inject constructor(
    private val shoppingItemRepository: ShoppingItemRepository,
) {
    operator fun invoke(shoppingItem: ShoppingItem): Flow<Result<Unit>> {
        return shoppingItemRepository.addShoppingItem(shoppingItem)
    }
}