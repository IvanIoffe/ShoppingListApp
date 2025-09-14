package com.ioffeivan.feature.login.data.source.remote

import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginApiService {

    @POST("Authentication")
    suspend fun login(
        @Query("key") authKey: String,
    ): Response<Unit>
}