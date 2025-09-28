package com.ioffeivan.feature.shopping_list.presentation.create_shopping_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ioffeivan.core.designsystem.component.PrimaryButton
import com.ioffeivan.core.designsystem.icon.PrimaryIcon
import com.ioffeivan.core.designsystem.icon.PrimaryIcons
import com.ioffeivan.core.ui.onDebounceClick
import com.ioffeivan.feature.shopping_list.R
import com.ioffeivan.feature.shopping_list.presentation.create_shopping_list.component.ShoppingListNameTextField

@Composable
fun CreateShoppingListRoute(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CreateShoppingListViewModel = hiltViewModel(),
) {
    val uiState by viewModel.createShoppingListUiState.collectAsStateWithLifecycle()

    CreateShoppingListScreen(
        uiState = uiState,
        onShoppingListNameChange = viewModel::onShoppingListNameChange,
        onCreateShoppingListClick = {
            viewModel.onCreateShoppingListClick()
            onBack()
        },
        onBackClick = onBack,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateShoppingListScreen(
    uiState: CreateShoppingListUiState,
    onShoppingListNameChange: (String) -> Unit,
    onCreateShoppingListClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.new_shopping_list),
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onDebounceClick(onBackClick)) {
                        PrimaryIcon(
                            icon = PrimaryIcons.Close,
                        )
                    }
                },
            )
        },
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
                    shoppingListName = uiState.name,
                    onShoppingListNameChange = onShoppingListNameChange,
                    modifier = Modifier
                        .fillMaxWidth(),
                )

                PrimaryButton(
                    text = stringResource(R.string.create_shopping_list),
                    onClick = {
                        onCreateShoppingListClick()
                    },
                    enabled = uiState.createShoppingListButtonEnabled,
                    modifier = Modifier
                        .fillMaxWidth(),
                )
            }
        }
    }
}