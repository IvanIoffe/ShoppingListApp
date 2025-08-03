package com.ioffeivan.feature.login.domain.repository

import com.ioffeivan.core.common.Result
import com.ioffeivan.feature.login.domain.model.LoginCredentials
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    fun login(loginCredentials: LoginCredentials): Flow<Result<Unit>>
}