package com.ioffeivan.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ioffeivan.core.database.model.ShoppingListOutboxEntity

@Dao
interface ShoppingListOutboxDao {

    @Query("SELECT * FROM shopping_lists_outbox")
    suspend fun getAllShoppingListsOutbox(): List<ShoppingListOutboxEntity>

    @Insert
    suspend fun insertShoppingListOutbox(shoppingListOutboxEntity: ShoppingListOutboxEntity)

    @Query("DELETE FROM shopping_lists_outbox WHERE id = :id")
    suspend fun deleteShoppingListOutbox(id: Int)
}