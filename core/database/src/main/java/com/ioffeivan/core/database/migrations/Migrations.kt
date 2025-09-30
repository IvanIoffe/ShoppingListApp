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

val MIGRATION_3_4 = object : Migration(3, 4) {
    override fun migrate(db: SupportSQLiteDatabase) {

        // Rename the old `shopping_lists` table
        db.execSQL("ALTER TABLE shopping_lists RENAME TO shopping_lists_old;")

        // Create a new `shopping_lists` table with the new schema
        db.execSQL(
            """
                CREATE TABLE shopping_lists (
                    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                    server_id INTEGER,
                    name TEXT NOT NULL
                );
            """
        )

        // Add a unique index to the server_id
        db.execSQL("CREATE UNIQUE INDEX index_shopping_lists_server_id ON shopping_lists(server_id);")

        // Copy data from the old table into the new one,
        // the old table's id goes into server_id. A new id will be generated automatically.
        db.execSQL(
            """
                INSERT INTO shopping_lists (server_id, name)
                SELECT id, name FROM shopping_lists_old;
            """
        )

        // Update foreign keys in `shopping_items`.
        // First create a temporary table to map old IDs to new IDs.
        db.execSQL(
            """
                CREATE TEMP TABLE id_map (
                    old_id INTEGER NOT NULL,
                    new_id INTEGER NOT NULL
                );
            """
        )

        // Fill it by mapping the old id (now server_id) to the new id
        db.execSQL(
            """
                INSERT INTO id_map (old_id, new_id)
                SELECT server_id, id FROM shopping_lists;
            """
        )

        // Now update `shopping_items` using the ID map
        db.execSQL(
            """
                UPDATE shopping_items
                SET list_id = (
                    SELECT new_id FROM id_map WHERE old_id = shopping_items.list_id
                );
            """
        )

        // Remove the old table
        db.execSQL("DROP TABLE shopping_lists_old;")
    }
}

val MIGRATION_4_5 = object : Migration(4, 5) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS `shopping_lists_outbox` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `list_id` INTEGER NOT NULL,
                `operation` TEXT NOT NULL,
                FOREIGN KEY(`list_id`) REFERENCES `shopping_lists`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
            )
        """.trimIndent()
        )
    }
}

val MIGRATION_5_6 = object : Migration(5, 6) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE temp_shopping_lists_outbox (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                list_id INTEGER NOT NULL,
                operation TEXT NOT NULL,
                FOREIGN KEY(list_id) REFERENCES shopping_lists(id) ON DELETE CASCADE
            )
        """.trimIndent()
        )

        db.execSQL(
            """
            INSERT INTO temp_shopping_lists_outbox (id, list_id, operation)
            SELECT id, list_id, operation FROM shopping_lists_outbox
        """.trimIndent()
        )

        db.execSQL("DROP TABLE shopping_lists_outbox")

        db.execSQL("ALTER TABLE temp_shopping_lists_outbox RENAME TO shopping_lists_outbox")
    }
}

val MIGRATION_6_7 = object : Migration(6, 7) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE shopping_items RENAME TO shopping_items_old;")

        db.execSQL(
            """
            CREATE TABLE shopping_items (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                server_id INTEGER,
                name TEXT NOT NULL,
                quantity TEXT NOT NULL,
                list_id INTEGER NOT NULL,
                FOREIGN KEY(list_id) REFERENCES shopping_lists(id) ON DELETE CASCADE
            );
        """.trimIndent()
        )

        db.execSQL("CREATE UNIQUE INDEX index_shopping_items_server_id ON shopping_items(server_id);")

        db.execSQL(
            """
            INSERT INTO shopping_items (server_id, name, quantity, list_id)
            SELECT id, name, quantity, list_id FROM shopping_items_old;
        """.trimIndent()
        )
        db.execSQL("DROP TABLE shopping_items_old;")
    }
}