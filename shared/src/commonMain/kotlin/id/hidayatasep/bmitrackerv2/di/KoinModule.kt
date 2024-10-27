package id.hidayatasep.bmitrackerv2.di

import id.hidayatasep.bmitrackerv2.data.datasource.impl.UserLocalDataSourceImpl
import id.hidayatasep.bmitrackerv2.data.datasource.UserLocalDataSource
import id.hidayatasep.bmitrackerv2.data.repository.UserRepository
import id.hidayatasep.bmitrackerv2.data.repository.impl.UserRepositoryImpl
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
            provideDataSourceModule,
            provideRepositoryModule,
            platformModule()
        )
    }

val provideDataSourceModule = module {
    singleOf(::UserLocalDataSourceImpl).bind(UserLocalDataSource::class)
}

val provideRepositoryModule = module {
    singleOf(::UserRepositoryImpl).bind(UserRepository::class)
}


