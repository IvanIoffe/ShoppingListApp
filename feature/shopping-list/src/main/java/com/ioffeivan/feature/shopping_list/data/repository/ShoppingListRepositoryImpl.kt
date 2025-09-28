package com.ioffeivan.feature.shopping_list.data.repository

import com.ioffeivan.core.common.Result
import com.ioffeivan.feature.shopping_list.data.mapper.toDomain
import com.ioffeivan.feature.shopping_list.data.mapper.toDto
import com.ioffeivan.feature.shopping_list.data.mapper.toEntity
import com.ioffeivan.feature.shopping_list.data.mapper.toShoppingListEntity
import com.ioffeivan.feature.shopping_list.data.source.local.ShoppingListLocalDataSource
import com.ioffeivan.feature.shopping_list.data.source.remote.ShoppingListRemoteDataSource
import com.ioffeivan.feature.shopping_list.data.source.remote.model.ShoppingListsDto
import com.ioffeivan.feature.shopping_list.domain.model.CreateShoppingList
import com.ioffeivan.feature.shopping_list.domain.model.ShoppingLists
import com.ioffeivan.feature.shopping_list.domain.repository.ShoppingListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import javax.inject.Inject

class ShoppingListRepositoryImpl @Inject constructor(
    private val shoppingListRemoteDataSource: ShoppingListRemoteDataSource,
    private val shoppingListLocalDataSource: ShoppingListLocalDataSource,
) : ShoppingListRepository {

    private val remoteShoppingListsFlow = MutableSharedFlow<Result<ShoppingLists>>(replay = 1)

    private val localShoppingListsFlow: Flow<Result<ShoppingLists>> =
        shoppingListLocalDataSource.observeAllShoppingLists()
            .map { result ->
                when (result) {
                    is Result.Success -> {
                        val domainItems = result.data.map { it.toDomain() }
                        Result.Success(ShoppingLists(items = domainItems))
                    }

                    Result.Loading -> Result.Loading
                    is Result.Error -> Result.Error(result.message)
                }
            }

    private val shoppingLists = merge(remoteShoppingListsFlow, localShoppingListsFlow)

    override suspend fun refreshShoppingLists() {
        shoppingListRemoteDataSource.getAllShoppingLists()
            .collect { result ->
                when (result) {
                    is Result.Success -> {
                        val shoppingListsDto: ShoppingListsDto = result.data

                        if (shoppingListsDto.items.isEmpty()) {
                            remoteShoppingListsFlow.emit(
                                Result.Success(ShoppingLists(emptyList()))
                            )
                        } else {
                            val shoppingListsEntity = shoppingListsDto.toEntity()
                            shoppingListLocalDataSource.upsertShoppingLists(shoppingListsEntity)
                        }
                    }

                    is Result.Loading -> remoteShoppingListsFlow.emit(Result.Loading)
                    is Result.Error -> remoteShoppingListsFlow.emit(Result.Error(result.message))
                }
            }
    }

    override fun createShoppingList(
        createShoppingList: CreateShoppingList,
    ): Flow<Result<Unit>> {
        return shoppingListRemoteDataSource.createShoppingList(createShoppingList.toDto())
            .map { result ->
                when (result) {
                    is Result.Success -> {
                        val shoppingListEntity =
                            result.data.toShoppingListEntity(createShoppingList)
                        shoppingListLocalDataSource.upsertShoppingList(shoppingListEntity)
                        Result.Success(Unit)
                    }

                    Result.Loading -> Result.Loading
                    is Result.Error -> Result.Error(result.message)
                }
            }
    }

    override suspend fun deleteShoppingList(id: Int) {
        shoppingListRemoteDataSource.deleteShoppingList(id)
            .collect { result ->
                when (result) {
                    is Result.Success -> {
                        shoppingListLocalDataSource.deleteShoppingList(id)
                    }

                    is Result.Error -> {
                        remoteShoppingListsFlow.emit(Result.Error(result.message))
                    }

                    else -> {}
                }
            }

    }

    override fun observeShoppingLists(): Flow<Result<ShoppingLists>> {
        return shoppingLists
    }
}