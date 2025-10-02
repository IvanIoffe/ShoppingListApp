package com.ioffeivan.core.database.dao

import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.ioffeivan.core.database.model.ShoppingItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingItemDao {

    @Query(
        value = """
            SELECT * FROM shopping_items
            WHERE list_id = :listId AND id NOT IN (
                SELECT item_id FROM shopping_items_outbox 
                WHERE operation = "DELETE"
            )
        """
    )
    fun observeShoppingItems(listId: Int): Flow<List<ShoppingItemEntity>>

    @Query(
        value = """
            SELECT * FROM shopping_items
            WHERE id = :id
        """
    )
    suspend fun getShoppingItem(id: Int): ShoppingItemEntity

    @Upsert
    suspend fun upsertShoppingItems(shoppingItems: List<ShoppingItemEntity>)

    @Upsert
    suspend fun upsertShoppingItem(shoppingItemEntity: ShoppingItemEntity): Long

    @Query(
        value = """
            DELETE FROM shopping_items
            WHERE id = :id
        """
    )
    suspend fun deleteShoppingItem(id: Int)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateShoppingItem(shoppingItemEntity: ShoppingItemEntity)
}