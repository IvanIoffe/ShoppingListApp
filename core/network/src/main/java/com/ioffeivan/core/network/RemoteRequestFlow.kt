package com.ioffeivan.core.network

import com.ioffeivan.core.common.Result
import com.ioffeivan.core.network.model.Error
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.json.Json
import retrofit2.Response

fun <T> remoteRequestFlow(call: suspend () -> Response<T>): Flow<Result<T>> {
    return flow {
        emit(Result.Loading)

        try {
            val response = call()

            if (response.isSuccessful) {
                response.body()?.let { data ->
                    emit(Result.Success(data))
                }
            } else {
                response.errorBody()?.let { error ->
                    val json = Json { ignoreUnknownKeys = true }
                    val parsedError = json.decodeFromString<Error>(error.string())
                    emit(Result.Error(message = parsedError.message))
                }
            }
        } catch (e: Exception) {
            emit(Result.Error(message = e.message ?: ""))
        }
    }.flowOn(Dispatchers.IO)
}