package com.ioffeivan.core.ui.utils

import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis


// Executes the given [block] of code, wrapping it with calls to [startRefreshingAction] and [endRefreshingAction].
// Ensures that at least [minShowTime] milliseconds elapse between the start and end,
// preventing the refresh indicator from "flickering" (appearing and disappearing too quickly).
suspend fun withRefreshing(
    minShowTimeMillis: Long = 100L,
    startRefreshingAction: suspend () -> Unit,
    endRefreshingAction: suspend () -> Unit,
    block: suspend () -> Unit,
) {
    var blockExecutionTime = 0L

    try {
        startRefreshingAction()
        blockExecutionTime = measureTimeMillis {
            block()
        }
    } finally {
        withContext(NonCancellable) {
            val remainingTime = minShowTimeMillis - blockExecutionTime
            if (remainingTime > 0) {
                delay(remainingTime)
            }
            endRefreshingAction()
        }
    }
}