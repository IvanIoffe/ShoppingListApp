package com.ioffeivan.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ioffeivan.core.database.dao.ShoppingListDao
import com.ioffeivan.core.database.model.ShoppingListEntity

@Database(
    entities = [ShoppingListEntity::class],
    version = 2,
    exportSchema = true,
)
internal abstract class SlaDatabase : RoomDatabase() {
    abstract fun shoppingListDao(): ShoppingListDao
}