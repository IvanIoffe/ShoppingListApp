package com.ioffeivan.feature.shopping_list.data.source.remote.di

import com.ioffeivan.core.network.di.Authorized
import com.ioffeivan.feature.shopping_list.data.source.remote.RetrofitShoppingListRemoteDataSource
import com.ioffeivan.feature.shopping_list.data.source.remote.ShoppingListApiService
import com.ioffeivan.feature.shopping_list.data.source.remote.ShoppingListRemoteDataSource
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
class ShoppingListRemoteSourceModuleProvider {

    @Singleton
    @Provides
    fun provideShoppingListApiService(@Authorized retrofit: Retrofit): ShoppingListApiService {
        return retrofit.create()
    }
}

@InstallIn(SingletonComponent::class)
@Module
interface ShoppingListRemoteSourceModuleBinder {

    @Binds
    fun bindShoppingListRemoteDataSource(
        impl: RetrofitShoppingListRemoteDataSource
    ): ShoppingListRemoteDataSource
}