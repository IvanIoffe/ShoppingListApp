package com.ioffeivan.feature.login.data.source.local

import com.ioffeivan.core.datastore_auth.AuthStore
import javax.inject.Inject

class DataStoreLoginLocalDataSource @Inject constructor(
    private val authStore: AuthStore,
) : LoginLocalDataSource {

    override val isLoggedIn = authStore.isLoggedIn

    override suspend fun saveAuthKey(authKey: String) {
        authStore.saveAuthKey(authKey)
    }
}