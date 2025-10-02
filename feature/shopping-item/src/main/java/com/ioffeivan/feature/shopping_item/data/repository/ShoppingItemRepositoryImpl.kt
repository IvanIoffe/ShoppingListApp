package com.ioffeivan.feature.shopping_item.data.repository

import com.ioffeivan.core.common.Result
import com.ioffeivan.core.database.dao.ShoppingItemOutboxDao
import com.ioffeivan.core.database.model.ShoppingItemEntity
import com.ioffeivan.core.database.model.ShoppingItemOutboxOperation
import com.ioffeivan.core.database.model.ShoppingItemOutboxEntity
import com.ioffeivan.feature.shopping_item.data.mapper.toDomain
import com.ioffeivan.feature.shopping_item.data.mapper.toEntities
import com.ioffeivan.feature.shopping_item.data.mapper.toEntity
import com.ioffeivan.feature.shopping_item.data.source.local.ShoppingItemLocalDataSource
import com.ioffeivan.feature.shopping_item.data.source.remote.ShoppingItemRemoteDataSource
import com.ioffeivan.feature.shopping_item.domain.model.ShoppingItem
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
    private val shoppingItemOutboxDao: ShoppingItemOutboxDao,
) : ShoppingItemRepository {

    private val remoteShoppingItemsFLow = MutableSharedFlow<Result<ShoppingItems>>(replay = 1)

    override suspend fun refreshShoppingItems(listLocalId: Int, listServerId: Int) {
        shoppingItemRemoteDataSource.getShoppingItems(listServerId)
            .collect { result ->
                when (result) {
                    is Result.Error -> remoteShoppingItemsFLow.emit(Result.Error(result.message))

                    Result.Loading -> remoteShoppingItemsFLow.emit(Result.Loading)

                    is Result.Success -> {
                        shoppingItemLocalDataSource.upsertShoppingItems(
                            result.data.toEntities(listLocalId)
                        )
                    }
                }
            }
    }

    override suspend fun addShoppingItem(shoppingItem: ShoppingItem) {
        val id = shoppingItemLocalDataSource.upsertShoppingItem(
            shoppingItem.toEntity()
        )
        shoppingItemOutboxDao.insertShoppingItemOutbox(
            shoppingItemOutboxEntity = ShoppingItemOutboxEntity(
                itemId = id.toInt(),
                operation = ShoppingItemOutboxOperation.ADD,
            )
        )
    }

    override suspend fun deleteShoppingItem(id: Int) {
        shoppingItemOutboxDao.insertShoppingItemOutbox(
            ShoppingItemOutboxEntity(
                itemId = id,
                operation = ShoppingItemOutboxOperation.DELETE,
            )
        )
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