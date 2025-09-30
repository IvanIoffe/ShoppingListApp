package com.ioffeivan.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ioffeivan.core.database.dao.ShoppingItemDao
import com.ioffeivan.core.database.dao.ShoppingItemOutboxDao
import com.ioffeivan.core.database.dao.ShoppingListDao
import com.ioffeivan.core.database.dao.ShoppingListOutboxDao
import com.ioffeivan.core.database.model.ShoppingItemEntity
import com.ioffeivan.core.database.model.ShoppingItemOutboxEntity
import com.ioffeivan.core.database.model.ShoppingListEntity
import com.ioffeivan.core.database.model.ShoppingListOutboxEntity

@Database(
    entities = [
        ShoppingListEntity::class,
        ShoppingListOutboxEntity::class,
        ShoppingItemEntity::class,
        ShoppingItemOutboxEntity::class,
    ],
    version = 8,
    exportSchema = true,
)
internal abstract class SlaDatabase : RoomDatabase() {
    abstract fun shoppingListDao(): ShoppingListDao
    abstract fun shoppingListOutboxDao(): ShoppingListOutboxDao
    abstract fun shoppingItemDao(): ShoppingItemDao
    abstract fun shoppingItemOutboxDao(): ShoppingItemOutboxDao
}