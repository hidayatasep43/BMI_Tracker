package id.hidayatasep.bmitrackerv2.di

import androidx.room.RoomDatabase
import id.hidayatasep.bmitrackerv2.database.AppDatabase
import id.hidayatasep.bmitrackerv2.getDatabaseBuilder
import org.koin.dsl.module

actual fun platformModule() = module {
    single<RoomDatabase.Builder<AppDatabase>> {
        getDatabaseBuilder()
    }
}