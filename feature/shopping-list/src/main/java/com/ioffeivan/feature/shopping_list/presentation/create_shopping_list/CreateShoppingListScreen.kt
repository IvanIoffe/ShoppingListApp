package com.ioffeivan.feature.shopping_list.presentation.create_shopping_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ioffeivan.core.designsystem.component.SlaButton
import com.ioffeivan.core.ui.LoadingScreen
import com.ioffeivan.core.ui.ObserveAsEventsWithLifecycle
import com.ioffeivan.feature.shopping_list.presentation.create_shopping_list.component.ShoppingListNameTextField

@Composable
fun CreateShoppingListRoute(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CreateShoppingListViewModel = hiltViewModel(),
) {
    val enteringShoppingListDataUiState by viewModel.enteringShoppingListDataUiState.collectAsStateWithLifecycle()
    val creatingShoppingListUiState by viewModel.creatingShoppingListUiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    ObserveAsEventsWithLifecycle(
        events = viewModel.createShoppingListEvent,
        onEvent = { event ->
            when (event) {
                is CreateShoppingListEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(event.message)
                }

                CreateShoppingListEvent.NavigateToBack -> onBack()
            }
        },
    )

    CreateShoppingListScreen(
        enteringShoppingListDataUiState = enteringShoppingListDataUiState,
        creatingShoppingListUiState = creatingShoppingListUiState,
        onShoppingListNameChange = viewModel::onShoppingListNameChange,
        onCreateShoppingListClick = viewModel::onCreateShoppingListClick,
        onBackClick = onBack,
        modifier = modifier,
        snackbarHostState = snackbarHostState,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateShoppingListScreen(
    enteringShoppingListDataUiState: EnteringShoppingListDataUiState,
    creatingShoppingListUiState: CreatingShoppingListUiState,
    onShoppingListNameChange: (String) -> Unit,
    onCreateShoppingListClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "New List",
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = null,
                        )
                    }
                },
            )
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .fillMaxSize(),
            ) {
                ShoppingListNameTextField(
                    shoppingListName = enteringShoppingListDataUiState.shoppingListName,
                    onShoppingListNameChange = onShoppingListNameChange,
                    modifier = Modifier
                        .fillMaxWidth(),
                )

                SlaButton(
                    text = "Create List",
                    onClick = {
                        onCreateShoppingListClick()
                    },
                    enabled = enteringShoppingListDataUiState.createShoppingListButtonEnabled,
                    modifier = Modifier
                        .fillMaxWidth(),
                )
            }
        }
    }

    when (creatingShoppingListUiState) {
        CreatingShoppingListUiState.Error,
        CreatingShoppingListUiState.Initial,
        CreatingShoppingListUiState.Success -> Unit

        CreatingShoppingListUiState.Loading -> {
            Popup(
                alignment = Alignment.Center,
            ) {
                LoadingScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(0.5f)),
                )
            }
        }
    }
}