package com.ioffeivan.feature.shopping_list.data.repository

import com.ioffeivan.core.common.Result
import com.ioffeivan.feature.shopping_list.data.mapper.toDomain
import com.ioffeivan.feature.shopping_list.data.mapper.toDto
import com.ioffeivan.feature.shopping_list.data.mapper.toShoppingListDomain
import com.ioffeivan.feature.shopping_list.data.source.remote.ShoppingListRemoteDataSource
import com.ioffeivan.feature.shopping_list.domain.model.CreateShoppingList
import com.ioffeivan.feature.shopping_list.domain.model.ShoppingList
import com.ioffeivan.feature.shopping_list.domain.model.ShoppingLists
import com.ioffeivan.feature.shopping_list.domain.repository.ShoppingListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ShoppingListRepositoryImpl @Inject constructor(
    private val shoppingListRemoteDataSource: ShoppingListRemoteDataSource,
) : ShoppingListRepository {

    override fun getAllShoppingLists(): Flow<Result<ShoppingLists>> {
        return shoppingListRemoteDataSource.getAllShoppingLists()
            .map { result ->
                when (result) {
                    is Result.Success -> {
                        Result.Success(result.data.toDomain())
                    }

                    Result.Loading -> Result.Loading
                    is Result.Error -> Result.Error(result.message)
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
}