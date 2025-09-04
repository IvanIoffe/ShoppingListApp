package com.ioffeivan.feature.shopping_item.presentation.shopping_items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ioffeivan.core.designsystem.preview.PreviewContainer
import com.ioffeivan.core.ui.LoadingScreen
import com.ioffeivan.core.ui.ObserveAsEventsWithLifecycle
import com.ioffeivan.feature.shopping_item.R
import com.ioffeivan.feature.shopping_item.domain.model.ShoppingItem
import com.ioffeivan.feature.shopping_item.domain.model.ShoppingItems
import com.ioffeivan.feature.shopping_item.presentation.shopping_items.component.ShoppingItemCard
import com.ioffeivan.feature.shopping_item.presentation.shopping_items.utils.ShoppingItemsPreviewParameterProvider

@Composable
fun ShoppingItemRoute(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ShoppingItemsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isRefreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    ObserveAsEventsWithLifecycle(
        events = viewModel.shoppingItemsEvent,
        onEvent = { event ->
            when (event) {
                is ShoppingItemsEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        },
    )

    ShoppingItemScreen(
        uiState = uiState,
        isRefreshing = isRefreshing,
        snackbarHostState = snackbarHostState,
        onBack = onBack,
        onRefresh = viewModel::refreshShoppingItems,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingItemScreen(
    uiState: ShoppingItemsUiState,
    isRefreshing: Boolean,
    snackbarHostState: SnackbarHostState,
    onBack: () -> Unit,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = uiState.title,
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
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
        PullToRefreshBox(
            isRefreshing = isRefreshing,
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
                    ShoppingItemScreenContent(
                        shoppingItems = uiState.shoppingItems.items,
                        modifier = Modifier
                            .fillMaxSize(),
                    )
                }

                uiState.isEmpty -> {
                    ShoppingItemScreenEmpty(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(horizontal = 8.dp),
                    )
                }
            }
        }
    }
}

@Composable
fun ShoppingItemScreenContent(
    shoppingItems: List<ShoppingItem>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(
            horizontal = 8.dp,
            vertical = 8.dp,
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(items = shoppingItems, key = { it.id }) { shoppingItem ->
            ShoppingItemCard(
                shoppingItem = shoppingItem,
            )
        }
    }
}

@Composable
fun ShoppingItemScreenEmpty(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.shopping_items_empty_title),
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.SemiBold
            ),
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.shopping_items_empty_message),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Preview
@Composable
fun ShoppingItemScreenContentPreviewLight(
    @PreviewParameter(ShoppingItemsPreviewParameterProvider::class)
    shoppingItems: ShoppingItems,
) {
    PreviewContainer {
        ShoppingItemScreen(
            uiState = ShoppingItemsUiState(
                title = "Party",
                shoppingItems = shoppingItems,
                isLoading = false,
            ),
            isRefreshing = false,
            snackbarHostState = SnackbarHostState(),
            onBack = {},
            onRefresh = {},
        )
    }
}

@Preview
@Composable
fun ShoppingItemScreenContentPreviewDark(
    @PreviewParameter(ShoppingItemsPreviewParameterProvider::class)
    shoppingItems: ShoppingItems,
) {
    PreviewContainer(darkTheme = true) {
        ShoppingItemScreen(
            uiState = ShoppingItemsUiState(
                title = "Party",
                shoppingItems = shoppingItems,
                isLoading = false,
            ),
            isRefreshing = false,
            snackbarHostState = SnackbarHostState(),
            onBack = {},
            onRefresh = {},
        )
    }
}

@Preview
@Composable
fun ShoppingItemScreenEmptyPreviewLight() {
    PreviewContainer {
        ShoppingItemScreen(
            uiState = ShoppingItemsUiState(
                title = "Party",
                isEmpty = true,
                isLoading = false,
            ),
            isRefreshing = false,
            snackbarHostState = SnackbarHostState(),
            onBack = {},
            onRefresh = {},
        )
    }
}

@Preview
@Composable
fun ShoppingItemScreenEmptyPreviewDark() {
    PreviewContainer(darkTheme = true) {
        ShoppingItemScreen(
            uiState = ShoppingItemsUiState(
                title = "Party",
                isEmpty = true,
                isLoading = false,
            ),
            isRefreshing = false,
            snackbarHostState = SnackbarHostState(),
            onBack = {},
            onRefresh = {},
        )
    }
}