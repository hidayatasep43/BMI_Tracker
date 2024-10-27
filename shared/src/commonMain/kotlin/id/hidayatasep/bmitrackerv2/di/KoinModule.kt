package id.hidayatasep.bmitrackerv2.di

import id.hidayatasep.bmitrackerv2.data.datasource.impl.UserLocalDataSourceImpl
import id.hidayatasep.bmitrackerv2.data.datasource.UserLocalDataSource
import id.hidayatasep.bmitrackerv2.data.repository.UserRepository
import id.hidayatasep.bmitrackerv2.data.repository.impl.UserRepositoryImpl
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
    singleOf(::UserLocalDataSourceImpl).bind(UserLocalDataSource::class)
}

val provideRepositoryModule = module {
    singleOf(::UserRepositoryImpl).bind(UserRepository::class)
}

val provideDatabaseModule = module {
    single { getRoomDatabase(get()) }
    single { getUserGrowthDao(get()) }
}



