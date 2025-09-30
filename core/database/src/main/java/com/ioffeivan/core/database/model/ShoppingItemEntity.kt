package com.ioffeivan.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "shopping_items",
    indices = [
        Index(value = ["server_id"], unique = true)
    ],
    foreignKeys = [
        ForeignKey(
            entity = ShoppingListEntity::class,
            parentColumns = ["id"],
            childColumns = ["list_id"],
            onDelete = ForeignKey.CASCADE,
        )
    ],
)
data class ShoppingItemEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "server_id")
    val serverId: Int? = null,

    val name: String,

    val quantity: String,

    @ColumnInfo("list_id")
    val listId: Int,
)