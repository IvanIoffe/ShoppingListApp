package com.ioffeivan.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

// This class describes the `shopping_lists_outbox` table in the database.
// It acts as an "outbox".
// All operations that need to be sent to the server are recorded here.
@Entity(
    tableName = "shopping_lists_outbox",
    foreignKeys = [
        ForeignKey(
            entity = ShoppingListEntity::class,
            parentColumns = ["id"],
            childColumns = ["list_id"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
data class ShoppingListOutboxEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "list_id")
    val listId: Int,

    val operation: Operation,
)

enum class Operation {
    CREATE,
    DELETE;
}