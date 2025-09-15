package com.ioffeivan.feature.shopping_list.data.source.local.di

import com.ioffeivan.feature.shopping_list.data.source.local.RoomShoppingListLocalDataSource
import com.ioffeivan.feature.shopping_list.data.source.local.ShoppingListLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface ShoppingListLocalSourceModuleBinder {

    @Binds
    fun bindShoppingListLocalDataSource(
        impl: RoomShoppingListLocalDataSource
    ): ShoppingListLocalDataSource
}