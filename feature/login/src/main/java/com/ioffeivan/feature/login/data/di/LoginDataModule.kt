package com.ioffeivan.feature.login.data.di

import com.ioffeivan.feature.login.data.repository.LoginRepositoryImpl
import com.ioffeivan.feature.login.domain.repository.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface LoginDataModuleBinder {

    @Binds
    fun bindLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository
}