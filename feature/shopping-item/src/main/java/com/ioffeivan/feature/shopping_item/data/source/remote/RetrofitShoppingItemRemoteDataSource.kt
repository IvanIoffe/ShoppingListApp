package com.ioffeivan.feature.shopping_item.data.source.remote

import com.ioffeivan.core.common.Result
import com.ioffeivan.core.network.remoteRequestFlow
import com.ioffeivan.feature.shopping_item.data.source.remote.model.AddShoppingItemDto
import com.ioffeivan.feature.shopping_item.data.source.remote.model.AddedShoppingItemDto
import com.ioffeivan.feature.shopping_item.data.source.remote.model.DeleteShoppingItemDto
import com.ioffeivan.feature.shopping_item.data.source.remote.model.ShoppingItemsDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RetrofitShoppingItemRemoteDataSource @Inject constructor(
    private val shoppingItemApiService: ShoppingItemApiService,
) : ShoppingItemRemoteDataSource {

    override fun addShoppingItem(
        addShoppingItemDto: AddShoppingItemDto,
    ): Flow<Result<AddedShoppingItemDto>> {
        return remoteRequestFlow {
            shoppingItemApiService.addShoppingItem(
                listId = addShoppingItemDto.listId,
                name = addShoppingItemDto.name,
                quantity = addShoppingItemDto.quantity,
            )
        }
    }

    override suspend fun deleteShoppingItem(
        deleteShoppingItemDto: DeleteShoppingItemDto,
    ): Flow<Result<Unit>> {
        return remoteRequestFlow {
            shoppingItemApiService.deleteShoppingItem(
                listId = deleteShoppingItemDto.listId,
                itemId = deleteShoppingItemDto.itemId,
            )
        }
    }

    override fun getShoppingItems(listId: Int): Flow<Result<ShoppingItemsDto>> {
        return remoteRequestFlow {
            shoppingItemApiService.getShoppingItems(listId)
        }
    }
}