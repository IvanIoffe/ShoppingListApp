package com.ioffeivan.feature.shopping_item.domain.repository

import com.ioffeivan.core.common.Result
import com.ioffeivan.feature.shopping_item.domain.model.CreateShoppingItem
import com.ioffeivan.feature.shopping_item.domain.model.DeleteShoppingItem
import com.ioffeivan.feature.shopping_item.domain.model.ShoppingItems
import kotlinx.coroutines.flow.Flow

interface ShoppingItemRepository {

    fun addShoppingItem(createShoppingItem: CreateShoppingItem): Flow<Result<Unit>>

    suspend fun deleteShoppingItem(deleteShoppingItem: DeleteShoppingItem)

    suspend fun refreshShoppingItems(listId: Int)

    fun observeShoppingItems(listId: Int): Flow<Result<ShoppingItems>>
}