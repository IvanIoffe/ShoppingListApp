package com.ioffeivan.core.network.interceptor

import com.ioffeivan.core.datastore_auth.AuthStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val authStore: AuthStore,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val authKey = runBlocking { authStore.authKey.first() }

        val originalRequest = chain.request()

        val newUrl = originalRequest.url.newBuilder()
            .addQueryParameter("key", authKey)
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}