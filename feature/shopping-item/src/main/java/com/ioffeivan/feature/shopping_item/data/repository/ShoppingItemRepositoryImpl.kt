package com.ioffeivan.feature.shopping_item.data.repository

import com.ioffeivan.core.common.Result
import com.ioffeivan.core.database.model.ShoppingItemEntity
import com.ioffeivan.feature.shopping_item.data.mapper.toDomain
import com.ioffeivan.feature.shopping_item.data.mapper.toEntities
import com.ioffeivan.feature.shopping_item.data.source.local.ShoppingItemLocalDataSource
import com.ioffeivan.feature.shopping_item.data.source.remote.ShoppingItemRemoteDataSource
import com.ioffeivan.feature.shopping_item.domain.model.ShoppingItem
import com.ioffeivan.feature.shopping_item.domain.model.ShoppingItems
import com.ioffeivan.feature.shopping_item.domain.repository.ShoppingItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import javax.inject.Inject

class ShoppingItemRepositoryImpl @Inject constructor(
    private val shoppingItemRemoteDataSource: ShoppingItemRemoteDataSource,
    private val shoppingItemLocalDataSource: ShoppingItemLocalDataSource,
) : ShoppingItemRepository {

    private val remoteShoppingItemsFLow = MutableSharedFlow<Result<ShoppingItems>>(replay = 1)

    override suspend fun refreshShoppingItems(listId: Int) {
        shoppingItemRemoteDataSource.getShoppingItems(listId)
            .collect { result ->
                when (result) {
                    is Result.Error -> remoteShoppingItemsFLow.emit(Result.Error(result.message))

                    Result.Loading -> remoteShoppingItemsFLow.emit(Result.Loading)

                    is Result.Success -> {
                        shoppingItemLocalDataSource.upsertShoppingItems(
                            result.data.toEntities(listId)
                        )
                    }
                }
            }
    }

    override fun addShoppingItem(shoppingItem: ShoppingItem): Flow<Result<Unit>> {
        return flowOf()
    }

    override suspend fun deleteShoppingItem(id: Int) {

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
                                ShoppingItems(items = result.data.map(ShoppingItemEntity::toDomain))
                            Result.Success(shoppingItems)
                        }
                    }
                },
        )
    }
}