package com.ioffeivan.sync.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.ioffeivan.core.database.dao.ShoppingListDao
import com.ioffeivan.core.database.dao.ShoppingListOutboxDao
import com.ioffeivan.core.database.model.Operation
import com.ioffeivan.feature.shopping_list.data.mapper.toDomain
import com.ioffeivan.feature.shopping_list.data.repository.ShoppingListSyncRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
internal class ShoppingListSyncWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val shoppingListSyncRepository: ShoppingListSyncRepository,
    private val shoppingListOutboxDao: ShoppingListOutboxDao,
    private val shoppingListDao: ShoppingListDao,
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            val outboxes = shoppingListOutboxDao.getAllShoppingListOutbox()

            outboxes.forEach { outbox ->
                val shoppingListEntity = shoppingListDao.getShoppingList(outbox.listId)

                when (outbox.operation) {
                    Operation.CREATE -> {
                        shoppingListSyncRepository.createShoppingList(shoppingListEntity.toDomain())
                        shoppingListOutboxDao.deleteShoppingListOutbox(outbox.id)
                    }

                    // For Operation.DELETE we don't need to explicitly remove the outbox row.
                    // The FK (shopping_lists_outbox.list_id -> shopping_lists.id) is defined with
                    // ON DELETE CASCADE, so deleting the ShoppingList will automatically remove
                    // the related shopping_lists_outbox row in the database.
                    Operation.DELETE -> {
                        shoppingListSyncRepository.deleteShoppingList(
                            localId = shoppingListEntity.id,
                            serverId = shoppingListEntity.serverId ?: 0,
                        )
                    }
                }
            }

            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

    companion object {
        fun startUpSyncWork(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<ShoppingListSyncWorker>()
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build()
                )
                .build()
        }
    }
}