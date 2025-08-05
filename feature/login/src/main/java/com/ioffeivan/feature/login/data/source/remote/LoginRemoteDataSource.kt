package com.ioffeivan.feature.login.data.source.remote

import com.ioffeivan.core.common.Result
import com.ioffeivan.feature.login.data.source.remote.model.LoginCredentialsDto
import com.ioffeivan.feature.login.data.source.remote.model.LoginResponseDto
import kotlinx.coroutines.flow.Flow

interface LoginRemoteDataSource {

    fun login(loginCredentialsDto: LoginCredentialsDto): Flow<Result<LoginResponseDto>>
}