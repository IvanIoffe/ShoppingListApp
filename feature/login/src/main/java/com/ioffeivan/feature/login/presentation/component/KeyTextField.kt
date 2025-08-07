package com.ioffeivan.feature.login.presentation.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ioffeivan.feature.login.R

@Composable
fun KeyTextField(
    key: String,
    onKeyChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isError: Boolean = false,
    errorMessage: String = "",
) {
    OutlinedTextField(
        value = key,
        onValueChange = onKeyChange,
        modifier = modifier,
        enabled = enabled,
        label = {
            Text(
                text = stringResource(R.string.key),
                style = MaterialTheme.typography.bodyLarge,
            )
        },
        placeholder = {
            Text(
                text = stringResource(R.string.enter_key),
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
        shape = RoundedCornerShape(16.dp),
    )
}