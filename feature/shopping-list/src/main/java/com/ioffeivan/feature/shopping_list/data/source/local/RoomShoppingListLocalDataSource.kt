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

    override fun getAllShoppingLists(): Flow<Result<List<ShoppingListEntity>>> {
        return shoppingListDao.getAllShoppingLists().toResultFlow()
    }

    override suspend fun addShoppingLists(shoppingLists: List<ShoppingListEntity>) {
        shoppingListDao.insertShoppingLists(shoppingLists)
    }

    override suspend fun addShoppingList(shoppingList: ShoppingListEntity) {
        shoppingListDao.insertShoppingList(shoppingList)
    }

    override suspend fun deleteShoppingList(shoppingList: ShoppingListEntity) {
        shoppingListDao.deleteShoppingList(shoppingList)
    }
}