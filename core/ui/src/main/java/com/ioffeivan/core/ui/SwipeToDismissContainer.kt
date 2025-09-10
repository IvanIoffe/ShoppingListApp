package com.ioffeivan.core.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun SwipeToDismissContainer(
    backgroundContent: @Composable (RowScope.(SwipeToDismissBoxState) -> Unit),
    modifier: Modifier = Modifier,
    enableDismissFromStartToEnd: Boolean = true,
    enableDismissFromEndToStart: Boolean = true,
    thresholdFraction: Float = 0.5f,
    confirmValueChange: (SwipeToDismissBoxValue) -> Boolean = { true },
    onStartToEnd: () -> Unit = {},
    onEndToStart: () -> Unit = {},
    content: @Composable (RowScope.() -> Unit),
) {
    val state = rememberSwipeToDismissBoxState(
        positionalThreshold = { totalDistance -> totalDistance * thresholdFraction },
        confirmValueChange = confirmValueChange,
    )

    LaunchedEffect(state) {
        snapshotFlow { state.currentValue }
            .distinctUntilChanged()
            .collect { value ->
                when (value) {
                    SwipeToDismissBoxValue.StartToEnd -> onStartToEnd()
                    SwipeToDismissBoxValue.EndToStart -> onEndToStart()
                    else -> {}
                }
                state.snapTo(SwipeToDismissBoxValue.Settled)
            }
    }

    SwipeToDismissBox(
        state = state,
        backgroundContent = { backgroundContent(state) },
        modifier = modifier,
        enableDismissFromStartToEnd = enableDismissFromStartToEnd,
        enableDismissFromEndToStart = enableDismissFromEndToStart,
        gesturesEnabled = enableDismissFromStartToEnd || enableDismissFromEndToStart,
        content = content,
    )
}