package com.ioffeivan.feature.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ioffeivan.core.common.Result
import com.ioffeivan.feature.login.domain.model.LoginCredentials
import com.ioffeivan.feature.login.domain.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState>
        get() = _uiState.asStateFlow()

    fun onKeyChange(key: String) {
        _uiState.update {
            it.copy(
                key = key,
                errorMessage = null,
            )
        }
    }

    fun login() {
        loginRepository.login(LoginCredentials(key = _uiState.value.key))
            .onEach { result ->
                when (result) {
                    is Result.Success -> {
                        _uiState.update {
                            it.copy(success = true)
                        }
                    }

                    is Result.Error -> {
                        _uiState.update {
                            it.copy(isLoading = false, errorMessage = result.message)
                        }
                    }

                    Result.Loading -> {
                        _uiState.update {
                            it.copy(isLoading = true)
                        }
                    }
                }
            }
            .launchIn(viewModelScope)
    }
}