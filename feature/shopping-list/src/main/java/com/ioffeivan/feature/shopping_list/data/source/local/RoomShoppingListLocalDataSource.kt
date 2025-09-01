package com.ioffeivan.feature.shopping_list.data.source.local

import com.ioffeivan.core.common.Result
import com.ioffeivan.core.common.toResultFlow
import com.ioffeivan.core.database.dao.ShoppingListDao
import com.ioffeivan.core.database.model.ShoppingListEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomShoppingListLocalDataSource @Inject constructor(
    private val shoppingListDao: ShoppingListDao,
) : ShoppingListLocalDataSource {

    override fun observeAllShoppingLists(): Flow<Result<List<ShoppingListEntity>>> {
        return shoppingListDao.observeAllShoppingLists().toResultFlow()
    }

    override suspend fun insertShoppingLists(shoppingLists: List<ShoppingListEntity>) {
        shoppingListDao.insertShoppingLists(shoppingLists)
    }

    override suspend fun insertShoppingList(shoppingList: ShoppingListEntity) {
        shoppingListDao.insertShoppingList(shoppingList)
    }

    override suspend fun deleteShoppingList(id: Int) {
        shoppingListDao.deleteShoppingList(id)
    }

    override suspend fun changePendingDeletionStatus(id: Int, isDeletionStatus: Boolean) {
        shoppingListDao.changePendingDeletionStatus(id, isDeletionStatus)
    }
}