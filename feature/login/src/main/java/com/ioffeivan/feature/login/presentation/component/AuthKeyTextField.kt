package com.ioffeivan.feature.login.presentation.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ioffeivan.core.designsystem.component.PrimaryTextField
import com.ioffeivan.core.designsystem.icon.PrimaryIcon
import com.ioffeivan.core.designsystem.icon.PrimaryIcons
import com.ioffeivan.feature.login.R

@Composable
fun AuthKeyTextField(
    authKey: String,
    onAuthKeyChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    PrimaryTextField(
        value = authKey,
        onValueChange = onAuthKeyChange,
        modifier = modifier,
        enabled = enabled,
        label = {
            Text(
                text = stringResource(R.string.auth_key),
                style = MaterialTheme.typography.bodyLarge,
            )
        },
        placeholder = {
            Text(
                text = stringResource(R.string.enter_auth_key),
                style = MaterialTheme.typography.bodyLarge,
            )
        },
        leadingIcon = {
            PrimaryIcon(
                icon = PrimaryIcons.Key,
            )
        },
        singleLine = true,
    )
}