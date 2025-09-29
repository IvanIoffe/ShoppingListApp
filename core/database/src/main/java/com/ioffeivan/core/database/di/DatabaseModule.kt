package com.ioffeivan.core.database.di

import android.content.Context
import androidx.room.Room
import com.ioffeivan.core.database.SlaDatabase
import com.ioffeivan.core.database.migrations.MIGRATION_1_2
import com.ioffeivan.core.database.migrations.MIGRATION_2_3
import com.ioffeivan.core.database.migrations.MIGRATION_3_4
import com.ioffeivan.core.database.migrations.MIGRATION_4_5
import com.ioffeivan.core.database.migrations.MIGRATION_5_6
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context,
    ): SlaDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = SlaDatabase::class.java,
            name = "sla-database",
        )
            .addMigrations(MIGRATION_1_2)
            .addMigrations(MIGRATION_2_3)
            .addMigrations(MIGRATION_3_4)
            .addMigrations(MIGRATION_4_5)
            .addMigrations(MIGRATION_5_6)
            .build()
    }
}