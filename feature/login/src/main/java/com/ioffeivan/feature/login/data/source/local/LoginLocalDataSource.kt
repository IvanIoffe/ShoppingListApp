package com.ioffeivan.feature.login.data.source.local

import kotlinx.coroutines.flow.Flow

interface LoginLocalDataSource {

    val isLoggedIn: Flow<Boolean>

    suspend fun saveAuthKey(authKey: String)
}