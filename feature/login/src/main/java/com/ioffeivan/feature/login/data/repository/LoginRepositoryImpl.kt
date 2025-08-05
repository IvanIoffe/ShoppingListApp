package com.ioffeivan.feature.login.data.repository

import com.ioffeivan.core.common.Result
import com.ioffeivan.feature.login.data.mapper.toDto
import com.ioffeivan.feature.login.data.source.remote.LoginRemoteDataSource
import com.ioffeivan.feature.login.domain.model.LoginCredentials
import com.ioffeivan.feature.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginRemoteDataSource: LoginRemoteDataSource,
) : LoginRepository {

    override fun login(loginCredentials: LoginCredentials): Flow<Result<Unit>> {
        return loginRemoteDataSource.login(loginCredentials.toDto())
            .map { result ->
                when (result) {
                    is Result.Success -> {
                        Result.Success(Unit)
                    }

                    is Result.Error -> Result.Error(result.message)
                    Result.Loading -> Result.Loading
                }
            }
    }
}