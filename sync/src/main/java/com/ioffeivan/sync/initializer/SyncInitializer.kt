package com.ioffeivan.sync.initializer

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.ioffeivan.sync.worker.ShoppingListSyncWorker

object Sync {

    fun initialize(context: Context) {
        WorkManager.getInstance(context).apply {
            beginUniqueWork(
                uniqueWorkName = SYNC_WORK_NAME,
                existingWorkPolicy = ExistingWorkPolicy.REPLACE,
                request = ShoppingListSyncWorker.startUpSyncWork(),
            )
                .enqueue()
        }
    }
}

internal const val SYNC_WORK_NAME = "SyncWork"