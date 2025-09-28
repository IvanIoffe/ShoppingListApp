package com.ioffeivan.core.database.dao

import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.ioffeivan.core.database.model.ShoppingListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDao {

    @Query("SELECT * FROM shopping_lists")
    fun observeAllShoppingLists(): Flow<List<ShoppingListEntity>>

    @Query("SELECT * FROM shopping_lists WHERE id = :id")
    suspend fun getShoppingList(id: Int): ShoppingListEntity

    @Upsert
    suspend fun upsertShoppingLists(shoppingLists: List<ShoppingListEntity>)

    @Upsert
    suspend fun upsertShoppingList(shoppingListEntity: ShoppingListEntity)

    @Query("DELETE FROM shopping_lists WHERE id = :id")
    suspend fun deleteShoppingList(id: Int)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateShoppingList(shoppingListEntity: ShoppingListEntity)
}