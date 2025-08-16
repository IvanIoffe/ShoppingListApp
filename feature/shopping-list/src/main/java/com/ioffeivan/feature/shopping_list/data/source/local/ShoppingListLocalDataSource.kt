package com.ioffeivan.feature.shopping_list.data.source.local

import com.ioffeivan.core.common.Result
import com.ioffeivan.core.database.model.ShoppingListEntity
import kotlinx.coroutines.flow.Flow

interface ShoppingListLocalDataSource {

    fun getAllShoppingLists(): Flow<Result<List<ShoppingListEntity>>>

    suspend fun addShoppingLists(shoppingLists: List<ShoppingListEntity>)

    suspend fun addShoppingList(shoppingList: ShoppingListEntity)

    suspend fun deleteShoppingList(shoppingList: ShoppingListEntity)
}