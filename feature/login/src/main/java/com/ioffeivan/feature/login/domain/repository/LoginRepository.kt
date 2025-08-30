package com.ioffeivan.feature.login.domain.repository

import com.ioffeivan.core.common.Result
import com.ioffeivan.feature.login.domain.model.LoginCredentials
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    val isLoggedIn: Flow<Boolean>

    fun login(loginCredentials: LoginCredentials): Flow<Result<Unit>>
}