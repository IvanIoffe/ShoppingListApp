package com.ioffeivan.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "shopping_lists",
    indices = [
        Index(value = ["server_id"], unique = true),
    ],
)
data class ShoppingListEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "server_id")
    val serverId: Int? = null,

    @ColumnInfo(name = "name")
    val name: String,
)