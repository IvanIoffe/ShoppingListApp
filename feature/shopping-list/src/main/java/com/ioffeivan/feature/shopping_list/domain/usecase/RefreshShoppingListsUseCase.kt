package com.ioffeivan.feature.shopping_list.domain.usecase

import com.ioffeivan.feature.shopping_list.domain.repository.ShoppingListRepository
import javax.inject.Inject

class RefreshShoppingListsUseCase @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository,
) {
    suspend operator fun invoke() {
        shoppingListRepository.refreshShoppingLists()
    }
}