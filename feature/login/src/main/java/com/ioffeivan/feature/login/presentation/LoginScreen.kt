package com.ioffeivan.feature.login.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ioffeivan.core.designsystem.preview.PreviewContainer
import com.ioffeivan.core.ui.LoadingButton
import com.ioffeivan.feature.login.R
import com.ioffeivan.feature.login.presentation.component.KeyTextField

@Composable
fun LoginRoute(
    onLoginSuccess: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.success) {
        if (uiState.success) {
            onLoginSuccess()
        }
    }

    LoginScreen(
        uiState = uiState,
        onKeyChange = viewModel::onKeyChange,
        onLoginButtonClick = viewModel::login,
        modifier = modifier
            .padding(horizontal = 16.dp),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    uiState: LoginUiState,
    onKeyChange: (String) -> Unit,
    onLoginButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.log_in),
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
            )
        },
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            LoginForm(
                uiState = uiState,
                onKeyChange = onKeyChange,
                onLoginClick = onLoginButtonClick,
            )
        }
    }
}

@Composable
fun LoginForm(
    uiState: LoginUiState,
    onKeyChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier,
    ) {
        KeyTextField(
            key = uiState.key,
            onKeyChange = onKeyChange,
            enabled = !uiState.isLoading,
            isError = uiState.errorMessage != null,
            errorMessage = uiState.errorMessage.orEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
        )

        Spacer(modifier = Modifier.height(8.dp))

        LoadingButton(
            isLoading = uiState.isLoading,
            onClick = {
                focusManager.clearFocus()
                onLoginClick()
            },
            enabled = uiState.isLoginButtonEnabled,
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(
                text = stringResource(R.string.log_in),
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}

@Preview
@Composable
fun LoginScreenPreviewLight() {
    PreviewContainer {
        LoginScreen(
            uiState = LoginUiState(),
            onKeyChange = {},
            onLoginButtonClick = {},
            modifier = Modifier
                .padding(horizontal = 16.dp),
        )
    }
}

@Preview
@Composable
fun LoginScreenPreviewDark() {
    PreviewContainer(darkTheme = true) {
        LoginScreen(
            uiState = LoginUiState(),
            onKeyChange = {},
            onLoginButtonClick = {},
            modifier = Modifier
                .padding(horizontal = 16.dp),
        )
    }
}