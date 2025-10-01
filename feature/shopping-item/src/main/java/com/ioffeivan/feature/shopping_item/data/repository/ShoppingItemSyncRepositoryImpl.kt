package com.ioffeivan.feature.shopping_item.data.repository

import com.ioffeivan.core.common.Result
import com.ioffeivan.feature.shopping_item.data.mapper.toAddShoppingItemDto
import com.ioffeivan.feature.shopping_item.data.mapper.toShoppingItemEntity
import com.ioffeivan.feature.shopping_item.data.source.local.ShoppingItemLocalDataSource
import com.ioffeivan.feature.shopping_item.data.source.remote.ShoppingItemRemoteDataSource
import com.ioffeivan.feature.shopping_item.data.source.remote.model.DeleteShoppingItemDto
import com.ioffeivan.feature.shopping_item.domain.model.ShoppingItem
import javax.inject.Inject

class ShoppingItemSyncRepositoryImpl @Inject constructor(
    private val shoppingItemRemoteDataSource: ShoppingItemRemoteDataSource,
    private val shoppingItemLocalDataSource: ShoppingItemLocalDataSource,
) : ShoppingItemSyncRepository {

    override suspend fun addShoppingItem(shoppingItem: ShoppingItem, listServerId: Int) {
        shoppingItemRemoteDataSource.addShoppingItem(shoppingItem.toAddShoppingItemDto(listServerId))
            .collect { result ->
                when (result) {
                    is Result.Success -> {
                        val shoppingItemEntity = result.data.toShoppingItemEntity(
                            shoppingItem = shoppingItem,
                        )
                        shoppingItemLocalDataSource.upsertShoppingItem(
                            shoppingItemEntity = shoppingItemEntity,
                        )
                    }

                    is Result.Error -> {
                        throw Exception()
                    }

                    else -> {}
                }
            }
    }

    override suspend fun deleteShoppingItem(
        itemLocalId: Int,
        itemServerId: Int,
        listServerId: Int
    ) {
        shoppingItemRemoteDataSource.deleteShoppingItem(
            deleteShoppingItemDto = DeleteShoppingItemDto(
                listId = listServerId,
                itemId = itemServerId,
            )
        ).collect { result ->
            when (result) {
                is Result.Success -> {
                    shoppingItemLocalDataSource.deleteShoppingItem(itemLocalId)
                }

                is Result.Error -> {
                    throw Exception()
                }

                else -> {}
            }
        }
    }
}