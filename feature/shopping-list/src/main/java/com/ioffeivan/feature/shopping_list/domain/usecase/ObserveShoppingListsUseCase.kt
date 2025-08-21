package com.ioffeivan.feature.shopping_list.domain.usecase

import com.ioffeivan.core.common.Result
import com.ioffeivan.feature.shopping_list.domain.model.ShoppingLists
import com.ioffeivan.feature.shopping_list.domain.repository.ShoppingListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveShoppingListsUseCase @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository,
) {

    operator fun invoke(): Flow<Result<ShoppingLists>> {
        return shoppingListRepository.observeShoppingLists()
    }
}