package com.ioffeivan.feature.shopping_list.data.source.local

import com.ioffeivan.core.common.Result
import com.ioffeivan.core.database.model.ShoppingListEntity
import kotlinx.coroutines.flow.Flow

interface ShoppingListLocalDataSource {

    fun observeAllShoppingLists(): Flow<Result<List<ShoppingListEntity>>>

    suspend fun insertShoppingLists(shoppingLists: List<ShoppingListEntity>)

    suspend fun insertShoppingList(shoppingList: ShoppingListEntity)

    suspend fun deleteShoppingList(id: Int)

    suspend fun changePendingDeletionStatus(id: Int, isDeletionStatus: Boolean)
}