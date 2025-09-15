package com.ioffeivan.feature.shopping_item.presentation.add_shopping_item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ioffeivan.core.designsystem.component.SlaButton
import com.ioffeivan.core.designsystem.icon.SlaIcon
import com.ioffeivan.core.designsystem.icon.SlaIcons
import com.ioffeivan.core.designsystem.preview.PreviewContainer
import com.ioffeivan.core.ui.LoadingScreen
import com.ioffeivan.core.ui.ObserveAsEventsWithLifecycle
import com.ioffeivan.core.ui.onDebounceClick
import com.ioffeivan.feature.shopping_item.R
import com.ioffeivan.feature.shopping_item.presentation.add_shopping_item.component.ShoppingItemNameTextField
import com.ioffeivan.feature.shopping_item.presentation.add_shopping_item.component.ShoppingItemQuantityTextField

@Composable
fun AddShoppingItemRoute(
    onBack: () -> Unit,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    viewModel: AddShoppingItemViewModel = hiltViewModel(),
) {
    val enteringShoppingItemInfoUiState by viewModel.enteringShoppingItemInfoUiState.collectAsStateWithLifecycle()
    val addShoppingItemUiState by viewModel.addShoppingItemUiState.collectAsStateWithLifecycle()

    ObserveAsEventsWithLifecycle(
        events = viewModel.addShoppingItemEvent,
        onEvent = { event ->
            when (event) {
                is AddShoppingItemEvent.ShowSnackbar -> {
                    onShowSnackbar(event.message, null)
                }

                AddShoppingItemEvent.NavigateToBack -> onBack()
            }
        },
    )

    AddShoppingItemScreen(
        enteringShoppingItemInfoUiState = enteringShoppingItemInfoUiState,
        addShoppingItemUiState = addShoppingItemUiState,
        onBackClick = onBack,
        onShoppingItemNameChange = viewModel::onShoppingItemNameChange,
        onShoppingItemQuantityChange = viewModel::onShoppingItemQuantityChange,
        onAddShoppingItemClick = viewModel::onAddShoppingItemClick,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddShoppingItemScreen(
    enteringShoppingItemInfoUiState: EnteringShoppingItemInfoUiState,
    addShoppingItemUiState: AddShoppingItemUiState,
    onBackClick: () -> Unit,
    onShoppingItemNameChange: (String) -> Unit,
    onShoppingItemQuantityChange: (String) -> Unit,
    onAddShoppingItemClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.new_shopping_item),
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onDebounceClick(onClick = onBackClick)) {
                        SlaIcon(
                            icon = SlaIcons.Close,
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        EnteringShoppingItemInfoScreen(
            uiState = enteringShoppingItemInfoUiState,
            onShoppingItemNameChange = onShoppingItemNameChange,
            onShoppingItemQuantityChange = onShoppingItemQuantityChange,
            onAddShoppingItemClick = onAddShoppingItemClick,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 16.dp),
        )
    }

    when (addShoppingItemUiState) {
        AddShoppingItemUiState.Initial -> Unit
        AddShoppingItemUiState.Loading -> {
            Popup(alignment = Alignment.Center) {
                LoadingScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(0.5f)),
                )
            }
        }
    }
}

@Composable
fun EnteringShoppingItemInfoScreen(
    uiState: EnteringShoppingItemInfoUiState,
    onShoppingItemNameChange: (String) -> Unit,
    onShoppingItemQuantityChange: (String) -> Unit,
    onAddShoppingItemClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f),
        ) {
            ShoppingItemNameTextField(
                shoppingItemName = uiState.name,
                onShoppingItemNameChange = onShoppingItemNameChange,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                ),
                modifier = Modifier
                    .fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(16.dp))

            ShoppingItemQuantityTextField(
                shoppingItemQuantity = uiState.quantity,
                onShoppingItemQuantityChange = onShoppingItemQuantityChange,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword,
                ),
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }

        SlaButton(
            text = stringResource(R.string.add_shopping_item),
            onClick = onDebounceClick(onClick = onAddShoppingItemClick),
            enabled = uiState.addShoppingItemButtonEnabled,
            modifier = Modifier
                .fillMaxWidth(),
        )
    }
}

@Preview
@Composable
fun AddShoppingItemScreenPreviewLight() {
    PreviewContainer {
        AddShoppingItemScreen(
            enteringShoppingItemInfoUiState = EnteringShoppingItemInfoUiState(),
            addShoppingItemUiState = AddShoppingItemUiState.Initial,
            onBackClick = {},
            onShoppingItemNameChange = {},
            onShoppingItemQuantityChange = {},
            onAddShoppingItemClick = {},
        )
    }
}

@Preview
@Composable
fun AddShoppingItemScreenPreviewDark() {
    PreviewContainer(darkTheme = true) {
        AddShoppingItemScreen(
            enteringShoppingItemInfoUiState = EnteringShoppingItemInfoUiState(),
            addShoppingItemUiState = AddShoppingItemUiState.Initial,
            onBackClick = {},
            onShoppingItemNameChange = {},
            onShoppingItemQuantityChange = {},
            onAddShoppingItemClick = {},
        )
    }
}