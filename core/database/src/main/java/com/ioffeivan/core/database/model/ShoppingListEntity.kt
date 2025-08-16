package com.ioffeivan.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "shopping_lists",
)
data class ShoppingListEntity(

    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,
)