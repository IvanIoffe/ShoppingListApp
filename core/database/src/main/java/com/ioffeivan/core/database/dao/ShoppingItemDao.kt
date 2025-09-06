package com.ioffeivan.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.ioffeivan.core.database.model.ShoppingItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingItems(shoppingItems: List<ShoppingItemEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingItem(shoppingItemEntity: ShoppingItemEntity)

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