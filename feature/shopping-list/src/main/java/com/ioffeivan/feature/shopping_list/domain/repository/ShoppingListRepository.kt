package com.ioffeivan.feature.shopping_list.domain.repository

import com.ioffeivan.core.common.Result
import com.ioffeivan.feature.shopping_list.domain.model.CreateShoppingList
import com.ioffeivan.feature.shopping_list.domain.model.ShoppingList
import com.ioffeivan.feature.shopping_list.domain.model.ShoppingLists
import kotlinx.coroutines.flow.Flow

interface ShoppingListRepository {

    suspend fun refreshShoppingLists()

    fun createShoppingList(createShoppingList: CreateShoppingList): Flow<Result<ShoppingList>>

    fun deleteShoppingList(shoppingList: ShoppingList): Flow<Result<Unit>>

    fun observeShoppingLists(): Flow<Result<ShoppingLists>>
}