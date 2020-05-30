package com.covidtracker.app

import android.app.Application
import com.covidtracker.di.module.appModule
import com.covidtracker.di.module.networkModule
import com.covidtracker.di.module.utilsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created on 26/05/20.
 */

class CovidTrackerApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CovidTrackerApp)
            modules(listOf(appModule, networkModule, utilsModule))
        }

    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
    }
}