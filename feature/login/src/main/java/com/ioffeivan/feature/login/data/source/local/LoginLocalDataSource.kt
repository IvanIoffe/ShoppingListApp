package com.ioffeivan.feature.login.data.source.local

interface LoginLocalDataSource {

    suspend fun saveAuthKey(authKey: String)
}