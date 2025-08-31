package com.ioffeivan.feature.shopping_item.data.source.remote

import com.ioffeivan.core.common.Result
import com.ioffeivan.feature.shopping_item.data.source.remote.model.CreateShoppingItemDto
import com.ioffeivan.feature.shopping_item.data.source.remote.model.CreatedShoppingItemDto
import com.ioffeivan.feature.shopping_item.data.source.remote.model.DeleteShoppingItemDto
import com.ioffeivan.feature.shopping_item.data.source.remote.model.ShoppingItemsDto
import kotlinx.coroutines.flow.Flow

interface ShoppingItemRemoteDataSource {

    fun addShoppingItem(
        createShoppingItemDto: CreateShoppingItemDto,
    ): Flow<Result<CreatedShoppingItemDto>>

    suspend fun deleteShoppingItem(
        deleteShoppingItemDto: DeleteShoppingItemDto,
    ): Flow<Result<Unit>>

    fun getShoppingItems(listId: Int): Flow<Result<ShoppingItemsDto>>
}