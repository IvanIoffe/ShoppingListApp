package com.ioffeivan.core.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

sealed class Result<out T> {
    data object Loading : Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
}

fun <T> Flow<T>.toResultFlow(): Flow<Result<T>> = map<T, Result<T>> { Result.Success(it) }
    .catch { emit(Result.Error(it.message.orEmpty())) }