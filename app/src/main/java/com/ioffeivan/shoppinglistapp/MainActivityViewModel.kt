package com.ioffeivan.shoppinglistapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ioffeivan.feature.login.domain.usecase.IsLoggedInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    isLoggedInUseCase: IsLoggedInUseCase,
) : ViewModel() {

    private var lastEvent = MutableStateFlow<MainActivityEvent?>(null)

    val events = isLoggedInUseCase()
        .map { isLoggedIn ->
            if (isLoggedIn) MainActivityEvent.NavigateToShoppingLists
            else MainActivityEvent.NavigateToLogin
        }
        .filter { event -> lastEvent.value != event }
        .onEach { event -> lastEvent.update { event } }
        .shareIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000L),
        )
}

sealed interface MainActivityEvent {
    data object NavigateToShoppingLists : MainActivityEvent
    data object NavigateToLogin : MainActivityEvent
}