package com.ioffeivan.core.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            "ALTER TABLE shopping_lists " +
                    "ADD COLUMN is_pending_deletion INTEGER NOT NULL DEFAULT 0"
        )
    }
}
val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS `shopping_items` (
                `id` INTEGER NOT NULL PRIMARY KEY,
                `name` TEXT NOT NULL,
                `quantity` TEXT NOT NULL,
                `is_pending_deletion` INTEGER NOT NULL DEFAULT 0
                `list_id` INTEGER NOT NULL,
                FOREIGN KEY(`list_id`) REFERENCES `shopping_lists`(`id`) ON DELETE CASCADE ON UPDATE NO ACTION
            )
        """.trimIndent()
        )
    }
}