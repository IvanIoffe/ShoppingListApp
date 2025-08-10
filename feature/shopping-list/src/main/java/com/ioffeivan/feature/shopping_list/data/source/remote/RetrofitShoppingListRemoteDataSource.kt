package com.ioffeivan.feature.shopping_list.data.source.remote

import com.ioffeivan.core.common.Result
import com.ioffeivan.core.network.remoteRequestFlow
import com.ioffeivan.feature.shopping_list.data.source.remote.model.CreateShoppingListDto
import com.ioffeivan.feature.shopping_list.data.source.remote.model.CreatedShoppingListDto
import com.ioffeivan.feature.shopping_list.data.source.remote.model.ShoppingListDto
import com.ioffeivan.feature.shopping_list.data.source.remote.model.ShoppingListsDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RetrofitShoppingListRemoteDataSource @Inject constructor(
    private val shoppingListApiService: ShoppingListApiService,
) : ShoppingListRemoteDataSource {

    override fun getAllShoppingLists(): Flow<Result<ShoppingListsDto>> {
        return remoteRequestFlow {
            shoppingListApiService.getAllShoppingLists()
        }
    }

    override fun createShoppingList(
        createShoppingListDto: CreateShoppingListDto,
    ): Flow<Result<CreatedShoppingListDto>> {
        return remoteRequestFlow {
            shoppingListApiService.createShoppingList(listName = createShoppingListDto.name)
        }
    }

    override fun deleteShoppingList(shoppingListDto: ShoppingListDto): Flow<Result<Unit>> {
        return remoteRequestFlow {
            shoppingListApiService.deleteShoppingList(listId = shoppingListDto.id)
        }
    }
}