package com.ioffeivan.feature.login.data.source.remote

import com.ioffeivan.core.common.Result
import com.ioffeivan.core.network.remoteRequestFlow
import com.ioffeivan.feature.login.data.source.remote.model.LoginCredentialsDto
import com.ioffeivan.feature.login.data.source.remote.model.LoginResponseDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RetrofitLoginRemoteDataSource @Inject constructor(
    private val loginApiService: LoginApiService,
) : LoginRemoteDataSource {

    override fun login(loginCredentialsDto: LoginCredentialsDto): Flow<Result<LoginResponseDto>> {
        return remoteRequestFlow {
            loginApiService.login(loginCredentialsDto.key)
        }
    }
}