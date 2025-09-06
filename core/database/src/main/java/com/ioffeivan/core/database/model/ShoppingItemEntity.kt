package com.ioffeivan.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "shopping_items",
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

    @PrimaryKey
    val id: Int,

    val name: String,

    val quantity: String,

    @ColumnInfo("is_pending_deletion")
    val isPendingDeletion: Boolean = false,

    @ColumnInfo("list_id")
    val listId: Int,
)