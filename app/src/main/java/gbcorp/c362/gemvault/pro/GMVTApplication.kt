package gbcorp.c362.gemvault.pro

import android.app.Application
import gbcorp.c362.gemvault.pro.di.dataModule
import gbcorp.c362.gemvault.pro.di.dispatcherModule
import gbcorp.c362.gemvault.pro.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class GMVTApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val appModules = dataModule + viewModule + dispatcherModule

        startKoin {
            androidLogger()
            androidContext(this@GMVTApplication)
            modules(appModules)
        }
    }
}