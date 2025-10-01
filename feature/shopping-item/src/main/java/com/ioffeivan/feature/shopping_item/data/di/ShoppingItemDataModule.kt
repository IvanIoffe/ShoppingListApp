package com.ioffeivan.feature.shopping_item.data.di

import com.ioffeivan.feature.shopping_item.data.repository.ShoppingItemRepositoryImpl
import com.ioffeivan.feature.shopping_item.data.repository.ShoppingItemSyncRepository
import com.ioffeivan.feature.shopping_item.data.repository.ShoppingItemSyncRepositoryImpl
import com.ioffeivan.feature.shopping_item.domain.repository.ShoppingItemRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface ShoppingItemDataModuleBinder {

    @Singleton
    @Binds
    fun bindShoppingItemRepository(
        impl: ShoppingItemRepositoryImpl
    ): ShoppingItemRepository

    @Binds
    fun bindShoppingItemSyncRepository(
        impl: ShoppingItemSyncRepositoryImpl
    ): ShoppingItemSyncRepository
}