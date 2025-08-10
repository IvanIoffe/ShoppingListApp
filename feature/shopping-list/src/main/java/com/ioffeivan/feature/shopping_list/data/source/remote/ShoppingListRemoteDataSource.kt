package com.ioffeivan.feature.shopping_list.data.source.remote

import com.ioffeivan.core.common.Result
import com.ioffeivan.feature.shopping_list.data.source.remote.model.CreateShoppingListDto
import com.ioffeivan.feature.shopping_list.data.source.remote.model.CreatedShoppingListDto
import com.ioffeivan.feature.shopping_list.data.source.remote.model.ShoppingListDto
import com.ioffeivan.feature.shopping_list.data.source.remote.model.ShoppingListsDto
import kotlinx.coroutines.flow.Flow

interface ShoppingListRemoteDataSource {

    fun getAllShoppingLists(): Flow<Result<ShoppingListsDto>>

    fun createShoppingList(
        createShoppingListDto: CreateShoppingListDto,
    ): Flow<Result<CreatedShoppingListDto>>

    fun deleteShoppingList(shoppingListDto: ShoppingListDto): Flow<Result<Unit>>
}