package com.ioffeivan.feature.shopping_list.presentation.shopping_lists

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ioffeivan.core.designsystem.component.AddFloatingActionButton
import com.ioffeivan.core.ui.LoadingScreen
import com.ioffeivan.core.ui.ObserveAsEventsWithLifecycle
import com.ioffeivan.feature.shopping_list.R
import com.ioffeivan.feature.shopping_list.domain.model.ShoppingList
import com.ioffeivan.feature.shopping_list.presentation.shopping_lists.component.ShoppingListItem

@Composable
fun ShoppingListsRoute(
    modifier: Modifier = Modifier,
    onShoppingListClick: (ShoppingList) -> Unit,
    onCreateShoppingListClick: () -> Unit,
    viewModel: ShoppingListsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    ObserveAsEventsWithLifecycle(
        events = viewModel.oneTimeEvent,
        onEvent = { event ->
            when (event) {
                is OneTimeEvent.ShowErrorSnackbar -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        },
    )

    ShoppingListsScreen(
        uiState = uiState,
        onRefresh = viewModel::refreshShoppingLists,
        onShoppingListClick = onShoppingListClick,
        onCreateShoppingListClick = onCreateShoppingListClick,
        onDeleteShoppingListClick = viewModel::deleteShoppingList,
        modifier = modifier,
        snackbarHostState = snackbarHostState,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListsScreen(
    uiState: ShoppingListsUiState,
    onRefresh: () -> Unit,
    onShoppingListClick: (ShoppingList) -> Unit,
    onCreateShoppingListClick: () -> Unit,
    onDeleteShoppingListClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.shopping_lists_content_title))
                },
            )
        },
        floatingActionButton = {
            ShoppingListsFAB(
                onClick = onCreateShoppingListClick,
                isVisible = !uiState.isLoading,
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { innerPadding ->
        PullToRefreshBox(
            isRefreshing = uiState.isRefreshing,
            onRefresh = onRefresh,
            modifier = Modifier
                .padding(innerPadding),
        ) {
            when {
                uiState.isLoading -> {
                    LoadingScreen(
                        modifier = Modifier
                            .fillMaxSize(),
                    )
                }

                !uiState.isEmpty -> {
                    ShoppingListsScreenContent(
                        shoppingLists = uiState.shoppingLists.items,
                        onShoppingListClick = onShoppingListClick,
                        onDeleteShoppingListClick = onDeleteShoppingListClick,
                        modifier = Modifier
                            .fillMaxSize(),
                    )
                }

                uiState.isEmpty -> {
                    ShoppingListsScreenEmpty(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 8.dp),
                    )
                }
            }
        }
    }
}

@Composable
fun ShoppingListsScreenContent(
    shoppingLists: List<ShoppingList>,
    onShoppingListClick: (ShoppingList) -> Unit,
    onDeleteShoppingListClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(items = shoppingLists) { shoppingList ->
            ShoppingListItem(
                shoppingList = shoppingList,
                onClick = onShoppingListClick,
                onDeleteClick = onDeleteShoppingListClick,
            )

            HorizontalDivider()
        }
    }
}

@Composable
fun ShoppingListsScreenEmpty(
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
    ) {
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_shopping_cart_off),
                    contentDescription = null,
                    modifier = Modifier
                        .size(125.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(R.string.shopping_lists_empty_title),
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.shopping_lists_empty_message),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}

@Composable
fun ShoppingListsFAB(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isVisible: Boolean = true,
) {
    AnimatedVisibility(
        visible = isVisible,
        modifier = modifier,
        enter = fadeIn() + scaleIn(),
    ) {
        AddFloatingActionButton(
            onClick = onClick,
        )
    }
}