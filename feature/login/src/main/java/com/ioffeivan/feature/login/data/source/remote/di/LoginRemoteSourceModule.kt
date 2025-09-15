package com.ioffeivan.feature.login.data.source.remote.di

import com.ioffeivan.core.network.di.Unauthorized
import com.ioffeivan.feature.login.data.source.remote.LoginApiService
import com.ioffeivan.feature.login.data.source.remote.LoginRemoteDataSource
import com.ioffeivan.feature.login.data.source.remote.RetrofitLoginRemoteDataSource
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
class LoginRemoteSourceModuleProvider {

    @Singleton
    @Provides
    fun provideLoginApiService(@Unauthorized retrofit: Retrofit): LoginApiService {
        return retrofit
            .create()
    }
}

@InstallIn(SingletonComponent::class)
@Module
interface LoginRemoteSourceModuleBinder {

    @Binds
    fun bindLoginRemoteDataSource(
        impl: RetrofitLoginRemoteDataSource
    ): LoginRemoteDataSource
}