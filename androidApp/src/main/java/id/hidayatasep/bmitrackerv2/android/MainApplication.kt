package id.hidayatasep.bmitrackerv2.android

import android.app.Application
import id.hidayatasep.bmitrackerv2.android.di.appModule
import id.hidayatasep.bmitrackerv2.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent

class MainApplication: Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModule)
        }
    }

}