package com.ioffeivan.feature.shopping_list.domain.usecase

import com.ioffeivan.feature.shopping_list.domain.model.CreateShoppingList
import com.ioffeivan.feature.shopping_list.domain.repository.ShoppingListRepository
import javax.inject.Inject

class CreateShoppingListUseCase @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository,
) {
    suspend operator fun invoke(createShoppingList: CreateShoppingList) {
        shoppingListRepository.createShoppingList(createShoppingList)
    }
}