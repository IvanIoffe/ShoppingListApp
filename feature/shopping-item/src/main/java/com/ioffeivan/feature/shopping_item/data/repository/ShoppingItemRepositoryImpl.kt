package com.ioffeivan.feature.shopping_item.data.repository

import com.ioffeivan.core.common.Result
import com.ioffeivan.feature.shopping_item.data.mapper.toDomain
import com.ioffeivan.feature.shopping_item.data.mapper.toDto
import com.ioffeivan.feature.shopping_item.data.mapper.toEntity
import com.ioffeivan.feature.shopping_item.data.source.local.ShoppingItemLocalDataSource
import com.ioffeivan.feature.shopping_item.data.source.remote.ShoppingItemRemoteDataSource
import com.ioffeivan.feature.shopping_item.domain.model.CreateShoppingItem
import com.ioffeivan.feature.shopping_item.domain.model.DeleteShoppingItem
import com.ioffeivan.feature.shopping_item.domain.model.ShoppingItems
import com.ioffeivan.feature.shopping_item.domain.repository.ShoppingItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import javax.inject.Inject

class ShoppingItemRepositoryImpl @Inject constructor(
    private val shoppingItemRemoteDataSource: ShoppingItemRemoteDataSource,
    private val shoppingItemLocalDataSource: ShoppingItemLocalDataSource,
) : ShoppingItemRepository {

    private val remoteShoppingItemsFLow = MutableSharedFlow<Result<ShoppingItems>>(replay = 1)

    override fun addShoppingItem(createShoppingItem: CreateShoppingItem): Flow<Result<Unit>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteShoppingItem(deleteShoppingItem: DeleteShoppingItem) {
        shoppingItemLocalDataSource.changePendingDeletionStatus(
            id = deleteShoppingItem.itemId,
            isPendingDeletion = true,
        )
        shoppingItemRemoteDataSource.deleteShoppingItem(deleteShoppingItem.toDto())
            .collect { result ->
                when (result) {
                    is Result.Success -> {
                        shoppingItemLocalDataSource.deleteShoppingItem(deleteShoppingItem.itemId)
                    }

                    is Result.Error -> {
                        shoppingItemLocalDataSource.changePendingDeletionStatus(
                            id = deleteShoppingItem.itemId,
                            isPendingDeletion = false,
                        )
                        remoteShoppingItemsFLow.emit(Result.Error(result.message))
                    }

                    else -> {}
                }
            }
    }

    override suspend fun refreshShoppingItems(listId: Int) {
        shoppingItemRemoteDataSource.getShoppingItems(listId)
            .collect { result ->
                when (result) {
                    is Result.Error -> remoteShoppingItemsFLow.emit(Result.Error(result.message))

                    Result.Loading -> remoteShoppingItemsFLow.emit(Result.Loading)

                    is Result.Success -> {
                        shoppingItemLocalDataSource.insertShoppingItems(result.data.toEntity(listId))
                    }
                }
            }
    }

    override fun observeShoppingItems(listId: Int): Flow<Result<ShoppingItems>> {
        return merge(
            remoteShoppingItemsFLow,
            shoppingItemLocalDataSource.observeShoppingItems(listId)
                .map { result ->
                    when (result) {
                        is Result.Error -> Result.Error(result.message)
                        Result.Loading -> Result.Loading
                        is Result.Success -> {
                            val shoppingItems =
                                ShoppingItems(items = result.data.map { it.toDomain() })
                            Result.Success(shoppingItems)
                        }
                    }
                },
        )
    }
}