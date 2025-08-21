package com.ioffeivan.feature.shopping_list.data.repository

import com.ioffeivan.core.common.Result
import com.ioffeivan.feature.shopping_list.data.mapper.toDomain
import com.ioffeivan.feature.shopping_list.data.mapper.toDto
import com.ioffeivan.feature.shopping_list.data.mapper.toEntity
import com.ioffeivan.feature.shopping_list.data.mapper.toShoppingListDomain
import com.ioffeivan.feature.shopping_list.data.source.local.ShoppingListLocalDataSource
import com.ioffeivan.feature.shopping_list.data.source.remote.ShoppingListRemoteDataSource
import com.ioffeivan.feature.shopping_list.domain.model.CreateShoppingList
import com.ioffeivan.feature.shopping_list.domain.model.ShoppingList
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

    private val networkShoppingListsFlow = MutableSharedFlow<Result<ShoppingLists>>(replay = 1)

    private val localShoppingListsFlow: Flow<Result<ShoppingLists>> =
        shoppingListLocalDataSource.getAllShoppingLists()
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

    private val shoppingLists = merge(networkShoppingListsFlow, localShoppingListsFlow)

    override suspend fun refreshShoppingLists() {
        shoppingListRemoteDataSource.getAllShoppingLists()
            .collect { result ->
                when (result) {
                    is Result.Success -> {
                        val shoppingListsEntity = result.data.toEntity()
                        shoppingListLocalDataSource.addShoppingLists(shoppingListsEntity)
                    }

                    is Result.Loading -> networkShoppingListsFlow.emit(Result.Loading)
                    is Result.Error -> networkShoppingListsFlow.emit(Result.Error(result.message))
                }
            }
    }

    override fun createShoppingList(
        createShoppingList: CreateShoppingList,
    ): Flow<Result<ShoppingList>> {
        return shoppingListRemoteDataSource.createShoppingList(createShoppingList.toDto())
            .map { result ->
                when (result) {
                    is Result.Success -> {
                        Result.Success(
                            result.data.toShoppingListDomain(name = createShoppingList.name)
                        )
                    }

                    Result.Loading -> Result.Loading
                    is Result.Error -> Result.Error(result.message)
                }
            }
    }

    override fun deleteShoppingList(shoppingList: ShoppingList): Flow<Result<Unit>> {
        return shoppingListRemoteDataSource.deleteShoppingList(shoppingList.toDto())
            .map { result ->
                when (result) {
                    is Result.Success -> {
                        Result.Success(Unit)
                    }

                    Result.Loading -> Result.Loading
                    is Result.Error -> Result.Error(result.message)
                }
            }
    }

    override fun observeShoppingLists(): Flow<Result<ShoppingLists>> {
        return shoppingLists
    }
}