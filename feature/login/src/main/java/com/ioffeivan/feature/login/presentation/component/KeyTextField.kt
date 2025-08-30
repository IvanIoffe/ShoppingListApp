package com.ioffeivan.feature.login.presentation.component

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.ioffeivan.core.designsystem.component.SlaTextField
import com.ioffeivan.feature.login.R

@Composable
fun AuthKeyTextField(
    authKey: String,
    onAuthKeyChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isError: Boolean = false,
    errorMessage: String = "",
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
            Icon(painter = painterResource(R.drawable.ic_key), contentDescription = null)
        },
        isError = isError,
        supportingText = {
            if (isError) {
                Text(
                    text = errorMessage,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        },
        singleLine = true,
    )
}