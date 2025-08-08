package com.ioffeivan.core.datastore_auth

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthStore @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {
    companion object {
        private val AUTH_KEY = stringPreferencesKey("auth_key")
    }

    val authKey: Flow<String?> = dataStore.data.map { it[AUTH_KEY] }

    suspend fun saveAuthKey(authKey: String) {
        dataStore.edit { preferences ->
            preferences[AUTH_KEY] = authKey
        }
    }
}