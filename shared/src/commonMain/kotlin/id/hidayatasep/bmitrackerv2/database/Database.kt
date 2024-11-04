package id.hidayatasep.bmitrackerv2.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import id.hidayatasep.bmitrackerv2.database.user_growth.UserGrowthEntity
import id.hidayatasep.bmitrackerv2.database.user_growth.UserGrowthDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(entities = [UserGrowthEntity::class], version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserGrowthDao(): UserGrowthDao
}

// The Room compiler generates the `actual` implementations.
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}

fun getRoomDatabase(
    builder: RoomDatabase.Builder<AppDatabase>
): AppDatabase {
    return builder
        .addMigrations()
        .fallbackToDestructiveMigrationOnDowngrade(true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}

fun getUserGrowthDao(appDatabase: AppDatabase) = appDatabase.getUserGrowthDao()