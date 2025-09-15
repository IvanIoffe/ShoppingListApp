package com.ioffeivan.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

// Returns a debounced click handler — a lambda that will call [onClick] only if at least
// [debounceTimeMillis] milliseconds have passed since the last accepted click.
// Use this debounced handler for all clickable UI elements to prevent rapid repeated actions.
@Composable
fun <T> onDebounceClick(
    onClick: (T) -> Unit,
    debounceTimeMillis: Long = 500L,
): (T) -> Unit {
    var lastClickTimeMillis by remember { mutableLongStateOf(0L) }

    return { param: T ->
        System.currentTimeMillis().let { currentTimeMillis ->
            if (currentTimeMillis - lastClickTimeMillis >= debounceTimeMillis) {
                lastClickTimeMillis = currentTimeMillis
                onClick(param)
            }
        }
    }
}

@Composable
fun onDebounceClick(
    onClick: () -> Unit,
    debounceTimeMillis: Long = 500L,
): () -> Unit {
    val handler = onDebounceClick<Unit>(
        onClick = { onClick() },
        debounceTimeMillis = debounceTimeMillis,
    )

    return { handler(Unit) }
}