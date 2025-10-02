package com.ioffeivan.feature.shopping_list.domain.repository

import com.ioffeivan.core.common.Result
import com.ioffeivan.feature.shopping_list.domain.model.CreateShoppingList
import com.ioffeivan.feature.shopping_list.domain.model.ShoppingLists
import kotlinx.coroutines.flow.Flow

interface ShoppingListRepository {

    suspend fun refreshShoppingLists()

    suspend fun createShoppingList(createShoppingList: CreateShoppingList)

    suspend fun deleteShoppingList(id: Int)

    fun observeShoppingLists(): Flow<Result<ShoppingLists>>
}