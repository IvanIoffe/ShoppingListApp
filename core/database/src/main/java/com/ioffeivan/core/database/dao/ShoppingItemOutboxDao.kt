package com.ioffeivan.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ioffeivan.core.database.model.ShoppingItemOutboxEntity

@Dao
interface ShoppingItemOutboxDao {

    @Query("SELECT * FROM shopping_items_outbox")
    suspend fun getAllShoppingItemsOutbox(): List<ShoppingItemOutboxEntity>

    @Insert
    suspend fun insertShoppingItemOutbox(shoppingItemOutboxEntity: ShoppingItemOutboxEntity)

    @Query("DELETE FROM shopping_items_outbox WHERE id = :id")
    suspend fun deleteShoppingItemOutbox(id: Int)
}