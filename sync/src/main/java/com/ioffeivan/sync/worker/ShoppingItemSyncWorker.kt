package com.ioffeivan.sync.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.ioffeivan.core.database.dao.ShoppingItemDao
import com.ioffeivan.core.database.dao.ShoppingItemOutboxDao
import com.ioffeivan.core.database.dao.ShoppingListDao
import com.ioffeivan.core.database.model.ShoppingItemOperation
import com.ioffeivan.feature.shopping_item.data.mapper.toDomain
import com.ioffeivan.feature.shopping_item.data.repository.ShoppingItemSyncRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
internal class ShoppingItemSyncWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val shoppingItemSyncRepository: ShoppingItemSyncRepository,
    private val shoppingItemOutboxDao: ShoppingItemOutboxDao,
    private val shoppingItemDao: ShoppingItemDao,
    private val shoppingListDao: ShoppingListDao,
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            shoppingItemOutboxDao.getAllShoppingItemsOutbox()
                .forEach { outbox ->
                    val shoppingItemEntity = shoppingItemDao.getShoppingItem(outbox.itemId)
                    val listServerId =
                        shoppingListDao.getShoppingList(shoppingItemEntity.listId).serverId ?: 0

                    when (outbox.operation) {
                        ShoppingItemOperation.CREATE -> {
                            shoppingItemSyncRepository.addShoppingItem(
                                shoppingItemEntity.toDomain(),
                                listServerId = listServerId,
                            )
                        }

                        ShoppingItemOperation.DELETE -> {
                            shoppingItemSyncRepository.deleteShoppingItem(
                                itemServerId = shoppingItemEntity.serverId ?: 0,
                                listServerId = listServerId,
                            )
                        }
                    }

                    shoppingItemOutboxDao.deleteShoppingItemOutbox(outbox.id)
                }


            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

    companion object {
        fun startUpSyncWork(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<ShoppingItemSyncWorker>()
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build()
                )
                .build()
        }
    }
}