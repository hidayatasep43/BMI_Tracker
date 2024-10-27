package id.hidayatasep.bmitrackerv2.di

import id.hidayatasep.bmitrackerv2.database.AppDatabase
import id.hidayatasep.bmitrackerv2.getDatabaseBuilder
import org.koin.dsl.module

actual fun platformModule() = module {
    single<AppDatabase> { getDatabaseBuilder(get()).build() }
}