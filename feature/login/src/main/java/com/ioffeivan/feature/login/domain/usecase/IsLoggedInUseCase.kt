package com.ioffeivan.feature.login.domain.usecase

import com.ioffeivan.feature.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsLoggedInUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
) {
    operator fun invoke(): Flow<Boolean> = loginRepository.isLoggedIn
}