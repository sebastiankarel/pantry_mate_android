package de.slapps.pantrymate

import android.app.Application
import de.slapps.pantrymate.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PantryMateApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PantryMateApplication)
            modules(appModule)
        }
    }
}