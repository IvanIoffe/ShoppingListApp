package com.ioffeivan.feature.login.presentation.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ioffeivan.core.designsystem.component.SlaTextField
import com.ioffeivan.core.designsystem.icon.SlaIcon
import com.ioffeivan.core.designsystem.icon.SlaIcons
import com.ioffeivan.feature.login.R

@Composable
fun AuthKeyTextField(
    authKey: String,
    onAuthKeyChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    SlaTextField(
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
            SlaIcon(
                icon = SlaIcons.Key,
            )
        },
        singleLine = true,
    )
}