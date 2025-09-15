package com.ioffeivan.feature.shopping_list.data.di

import com.ioffeivan.feature.shopping_list.data.repository.ShoppingListRepositoryImpl
import com.ioffeivan.feature.shopping_list.domain.repository.ShoppingListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface ShoppingListDataModuleBinder {

    @Singleton
    @Binds
    fun bindShoppingListRepository(
        impl: ShoppingListRepositoryImpl
    ): ShoppingListRepository
}