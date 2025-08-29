package com.ioffeivan.feature.shopping_list.domain.usecase

import com.ioffeivan.core.common.Result
import com.ioffeivan.feature.shopping_list.domain.model.CreateShoppingList
import com.ioffeivan.feature.shopping_list.domain.repository.ShoppingListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateShoppingListUseCase @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository,
) {

    operator fun invoke(createShoppingList: CreateShoppingList): Flow<Result<Unit>> {
        return shoppingListRepository.createShoppingList(createShoppingList)
    }
}