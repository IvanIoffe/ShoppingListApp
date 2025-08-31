package com.ioffeivan.feature.shopping_item.data.source.local

import com.ioffeivan.core.common.Result
import com.ioffeivan.core.common.toResultFlow
import com.ioffeivan.core.database.dao.ShoppingItemDao
import com.ioffeivan.core.database.model.ShoppingItemEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomShoppingItemLocalDataSource @Inject constructor(
    private val shoppingItemDao: ShoppingItemDao,
) : ShoppingItemLocalDataSource {

    override suspend fun insertShoppingItems(shoppingItems: List<ShoppingItemEntity>) {
        shoppingItemDao.insertShoppingItems(shoppingItems)
    }

    override suspend fun insertShoppingItem(shoppingItemEntity: ShoppingItemEntity) {
        shoppingItemDao.insertShoppingItem(shoppingItemEntity)
    }

    override suspend fun deleteShoppingItem(id: Int) {
        shoppingItemDao.deleteShoppingItem(id)
    }

    override fun observeShoppingItems(listId: Int): Flow<Result<List<ShoppingItemEntity>>> {
        return shoppingItemDao.observeShoppingItems(listId).toResultFlow()
    }
}