package com.ioffeivan.feature.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ioffeivan.core.common.Result
import com.ioffeivan.feature.login.domain.model.LoginCredentials
import com.ioffeivan.feature.login.domain.usecase.LoginUseCase
import com.ioffeivan.feature.login.presentation.mapper.toLoginCredentials
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _loginEvent = Channel<LoginEvent>()
    val loginEvent = _loginEvent.receiveAsFlow()

    private val _loginTrigger = MutableSharedFlow<LoginCredentials>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )

    private val _inputState = MutableStateFlow(LoginUiState())

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _loginState: Flow<Result<Unit>?> = _loginTrigger
        .flatMapLatest { loginCredentials ->
            loginUseCase(loginCredentials)
        }
        .onEach { result ->
            when (result) {
                is Result.Error -> _loginEvent.send(LoginEvent.ShowSnackbar(result.message))
                else -> {}
            }
        }

    val uiState: StateFlow<LoginUiState> = combine(
        _inputState,
        _loginState.onStart { emit(null) },
    ) { input, result ->
        input.copy(
            isLoading = result is Result.Loading,
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000L),
        LoginUiState(),
    )

    fun onAuthKeyChange(authKey: String) {
        _inputState.update {
            it.copy(authKey = authKey)
        }
    }

    fun login() {
        _loginTrigger.tryEmit(_inputState.value.toLoginCredentials())
    }
}

sealed interface LoginEvent {
    data class ShowSnackbar(val message: String) : LoginEvent
}