package com.ioffeivan.feature.shopping_list.domain.usecase

import com.ioffeivan.core.common.Result
import com.ioffeivan.feature.shopping_list.domain.model.ShoppingList
import com.ioffeivan.feature.shopping_list.domain.repository.ShoppingListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteShoppingListUseCase @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository,
) {

    operator fun invoke(shoppingList: ShoppingList): Flow<Result<Unit>> {
        return shoppingListRepository.deleteShoppingList(shoppingList)
    }
}