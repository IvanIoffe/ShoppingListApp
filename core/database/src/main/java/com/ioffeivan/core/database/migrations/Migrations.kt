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