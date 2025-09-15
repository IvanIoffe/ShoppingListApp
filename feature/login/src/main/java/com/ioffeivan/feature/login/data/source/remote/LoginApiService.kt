package com.ioffeivan.feature.login.data.source.remote

import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginApiService {

    @POST("Authentication")
    suspend fun login(
        @Query("key") authKey: String,
    ): Response<Unit>

    // Preferred: send credentials in request body (POST with @Body) — more secure and RESTful,
    // but backend currently only accepts credentials via query parameters (@Query).
    /*@POST("Authentication")
    suspend fun login(
        @Body loginCredentialsDto: LoginCredentialsDto,
    ): Response<Unit>*/
}