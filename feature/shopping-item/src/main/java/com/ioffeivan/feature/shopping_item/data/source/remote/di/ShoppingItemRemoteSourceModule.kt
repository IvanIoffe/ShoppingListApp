package com.ioffeivan.feature.shopping_item.data.source.remote.di

import com.ioffeivan.core.network.di.Authorized
import com.ioffeivan.feature.shopping_item.data.source.remote.RetrofitShoppingItemRemoteDataSource
import com.ioffeivan.feature.shopping_item.data.source.remote.ShoppingItemApiService
import com.ioffeivan.feature.shopping_item.data.source.remote.ShoppingItemRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ShoppingItemRemoteSourceModuleProvider {

    @Singleton
    @Provides
    fun provideShoppingItemApiService(@Authorized retrofit: Retrofit): ShoppingItemApiService {
        return retrofit.create()
    }
}

@InstallIn(SingletonComponent::class)
@Module
interface ShoppingItemRemoteSourceModuleBinder {

    @Binds
    fun bindShoppingItemRemoteDataSource(
        impl: RetrofitShoppingItemRemoteDataSource
    ): ShoppingItemRemoteDataSource
}