package com.ioffeivan.feature.shopping_list.domain.usecase

import com.ioffeivan.feature.shopping_list.domain.repository.ShoppingListRepository
import javax.inject.Inject

class DeleteShoppingListUseCase @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository,
) {

    suspend operator fun invoke(id: Int) {
        return shoppingListRepository.deleteShoppingList(id)
    }
}