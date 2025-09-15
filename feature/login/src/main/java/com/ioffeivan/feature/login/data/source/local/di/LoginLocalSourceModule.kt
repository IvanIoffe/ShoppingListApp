package com.ioffeivan.feature.login.data.source.local.di

import com.ioffeivan.feature.login.data.source.local.DataStoreLoginLocalDataSource
import com.ioffeivan.feature.login.data.source.local.LoginLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface LoginLocalSourceModuleBinder {

    @Binds
    fun bindLoginLocalDataSource(
        impl: DataStoreLoginLocalDataSource
    ): LoginLocalDataSource
}