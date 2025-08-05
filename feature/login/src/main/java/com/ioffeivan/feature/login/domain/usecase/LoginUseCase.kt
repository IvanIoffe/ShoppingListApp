package com.ioffeivan.feature.login.domain.usecase

import com.ioffeivan.core.common.Result
import com.ioffeivan.feature.login.domain.model.LoginCredentials
import com.ioffeivan.feature.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
) {
    operator fun invoke(loginCredentials: LoginCredentials): Flow<Result<Unit>> {
        return loginRepository.login(loginCredentials)
    }
}