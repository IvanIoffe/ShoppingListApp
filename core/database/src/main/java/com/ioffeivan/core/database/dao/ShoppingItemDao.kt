package com.ioffeivan.core.database.dao

import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import com.ioffeivan.core.database.model.ShoppingItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingItemDao {

    @Upsert
    suspend fun upsertShoppingItems(shoppingItems: List<ShoppingItemEntity>)

    @Upsert
    suspend fun upsertShoppingItem(shoppingItemEntity: ShoppingItemEntity)

    @Query("DELETE FROM shopping_items WHERE id = :id")
    suspend fun deleteShoppingItem(id: Int)

    @Query("SELECT * FROM shopping_items WHERE list_id = :listId")
    fun observeShoppingItems(listId: Int): Flow<List<ShoppingItemEntity>>

    @Query("SELECT * FROM shopping_items WHERE id = :id")
    suspend fun getShoppingItem(id: Int): ShoppingItemEntity

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateShoppingItem(shoppingItemEntity: ShoppingItemEntity)

    @Transaction
    suspend fun changePendingDeletionStatus(id: Int, isPendingDeletion: Boolean) {
        val shoppingItem = getShoppingItem(id)
        updateShoppingItem(shoppingItem.copy(isPendingDeletion = isPendingDeletion))
    }
}