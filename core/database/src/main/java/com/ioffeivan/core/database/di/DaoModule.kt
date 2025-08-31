package com.ioffeivan.core.database.di

import com.ioffeivan.core.database.SlaDatabase
import com.ioffeivan.core.database.dao.ShoppingItemDao
import com.ioffeivan.core.database.dao.ShoppingListDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal object DaoModule {

    @Provides
    fun provideShoppingListDao(
        slaDatabase: SlaDatabase,
    ): ShoppingListDao = slaDatabase.shoppingListDao()

    @Provides
    fun provideShoppingItemDao(
        slaDatabase: SlaDatabase,
    ): ShoppingItemDao = slaDatabase.shoppingItemDao()
}