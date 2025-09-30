package com.ioffeivan.feature.shopping_item.domain.repository

import com.ioffeivan.core.common.Result
import com.ioffeivan.feature.shopping_item.domain.model.ShoppingItem
import com.ioffeivan.feature.shopping_item.domain.model.ShoppingItems
import kotlinx.coroutines.flow.Flow

interface ShoppingItemRepository {

    suspend fun refreshShoppingItems(listId: Int)

    fun addShoppingItem(shoppingItem: ShoppingItem): Flow<Result<Unit>>

    suspend fun deleteShoppingItem(id: Int)

    fun observeShoppingItems(listId: Int): Flow<Result<ShoppingItems>>
}