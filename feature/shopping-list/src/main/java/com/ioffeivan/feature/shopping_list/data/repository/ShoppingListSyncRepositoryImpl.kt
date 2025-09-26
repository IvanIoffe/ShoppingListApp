package com.ioffeivan.feature.shopping_list.data.repository

import com.ioffeivan.core.common.Result
import com.ioffeivan.feature.shopping_list.data.mapper.toCreateShoppingListDto
import com.ioffeivan.feature.shopping_list.data.mapper.toShoppingListEntity
import com.ioffeivan.feature.shopping_list.data.source.local.ShoppingListLocalDataSource
import com.ioffeivan.feature.shopping_list.data.source.remote.ShoppingListRemoteDataSource
import com.ioffeivan.feature.shopping_list.domain.model.ShoppingList
import kotlinx.coroutines.flow.filterNot
import javax.inject.Inject

class ShoppingListSyncRepositoryImpl @Inject constructor(
    private val shoppingListRemoteDataSource: ShoppingListRemoteDataSource,
    private val shoppingListLocalDataSource: ShoppingListLocalDataSource,
) : ShoppingListSyncRepository {

    override suspend fun createShoppingList(shoppingList: ShoppingList) {
        shoppingListRemoteDataSource.createShoppingList(shoppingList.toCreateShoppingListDto())
            .filterNot { it is Result.Loading }
            .collect { result ->
                when (result) {
                    is Result.Success -> {
                        val shoppingListEntity = result.data.toShoppingListEntity(shoppingList)
                        shoppingListLocalDataSource.insertShoppingList(
                            shoppingList = shoppingListEntity,
                        )
                    }

                    is Result.Error -> {
                        throw Exception()
                    }

                    else -> {}
                }
            }
    }

    override suspend fun deleteShoppingList(localId: Int, serverId: Int) {
        shoppingListRemoteDataSource.deleteShoppingList(id = serverId)
            .filterNot { it is Result.Loading }
            .collect { result ->
                when (result) {
                    is Result.Success -> {
                        shoppingListLocalDataSource.deleteShoppingList(id = localId)
                    }

                    is Result.Error -> {
                        throw Exception()
                    }

                    else -> {}
                }
            }
    }
}