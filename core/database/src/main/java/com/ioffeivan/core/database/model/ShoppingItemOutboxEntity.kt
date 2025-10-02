package com.ioffeivan.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

// This class describes the `shopping_items_outbox` table in the database.
// It acts as an "outbox".
// All operations that need to be sent to the server are recorded here.
@Entity(
    tableName = "shopping_items_outbox",
    foreignKeys = [
        ForeignKey(
            entity = ShoppingItemEntity::class,
            parentColumns = ["id"],
            childColumns = ["item_id"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
data class ShoppingItemOutboxEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "item_id")
    val itemId: Int,

    val operation: ShoppingItemOutboxOperation,
)

enum class ShoppingItemOutboxOperation {
    ADD,
    DELETE,
}