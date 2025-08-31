package com.ioffeivan.feature.shopping_item.data.source.local

import com.ioffeivan.core.common.Result
import com.ioffeivan.core.database.model.ShoppingItemEntity
import kotlinx.coroutines.flow.Flow

interface ShoppingItemLocalDataSource {

    suspend fun insertShoppingItems(shoppingItems: List<ShoppingItemEntity>)

    suspend fun insertShoppingItem(shoppingItemEntity: ShoppingItemEntity)

    suspend fun deleteShoppingItem(id: Int)

    fun observeShoppingItems(listId: Int): Flow<Result<List<ShoppingItemEntity>>>
}