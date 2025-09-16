package com.ioffeivan.core.database.dao

import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import com.ioffeivan.core.database.model.ShoppingListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDao {

    @Query("SELECT * FROM shopping_lists")
    fun observeAllShoppingLists(): Flow<List<ShoppingListEntity>>

    @Upsert
    suspend fun insertShoppingLists(shoppingLists: List<ShoppingListEntity>)

    @Upsert
    suspend fun insertShoppingList(shoppingListEntity: ShoppingListEntity)

    @Query("DELETE FROM shopping_lists WHERE id = :id")
    suspend fun deleteShoppingList(id: Int)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateShoppingList(shoppingListEntity: ShoppingListEntity)

    @Query("SELECT * FROM shopping_lists WHERE id = :id")
    suspend fun getShoppingList(id: Int): ShoppingListEntity

    @Transaction
    suspend fun changePendingDeletionStatus(id: Int, isPendingDeletion: Boolean) {
        val shoppingList = getShoppingList(id)
        updateShoppingList(shoppingList.copy(isPendingDeletion = isPendingDeletion))
    }
}