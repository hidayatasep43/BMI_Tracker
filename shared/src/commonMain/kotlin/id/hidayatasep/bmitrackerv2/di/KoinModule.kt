package id.hidayatasep.bmitrackerv2.di

import id.hidayatasep.bmitrackerv2.data.datasource.GrowthLocalDataSource
import id.hidayatasep.bmitrackerv2.data.repository.GrowthRepository
import id.hidayatasep.bmitrackerv2.data.repository.impl.GrowthRepositoryImpl
import id.hidayatasep.bmitrackerv2.data.datasource.impl.UserGrowthLocalDataSourceImpl
import id.hidayatasep.bmitrackerv2.database.getRoomDatabase
import id.hidayatasep.bmitrackerv2.database.getUserGrowthDao
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module

expect fun platformModule(): Module

fun initKoin(config: KoinAppDeclaration? = null) =
    startKoin {
        config?.invoke(this)
        modules(
            platformModule(),
            provideDataSourceModule,
            provideRepositoryModule,
            provideDatabaseModule
        )
    }

val provideDataSourceModule = module {
    singleOf(::UserGrowthLocalDataSourceImpl).bind(GrowthLocalDataSource::class)
}

val provideRepositoryModule = module {
    singleOf(::GrowthRepositoryImpl).bind(GrowthRepository::class)
}

val provideDatabaseModule = module {
    single { getRoomDatabase(get()) }
    single { getUserGrowthDao(get()) }
}



