package com.ioffeivan.feature.login.data.source.remote.di

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
    fun provideLoginApiService(retrofitBuilder: Retrofit.Builder): LoginApiService {
        return retrofitBuilder
            .build()
            .create()
    }
}

@InstallIn(SingletonComponent::class)
@Module
interface LoginRemoteSourceModuleBinder {

    @Binds
    fun bindLoginRemoteDataSource(
        retrofitLoginRemoteDataSource: RetrofitLoginRemoteDataSource
    ): LoginRemoteDataSource
}