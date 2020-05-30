package com.covidtracker.di.module

import android.app.Application
import com.covidtracker.utils.NetworkUtils
import com.covidtracker.utils.PreferenceUtils
import com.google.gson.Gson
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * Created on 28/05/20.
 */
// Define all utilities models here
val utilsModule = module {
    single { provideNetworkUtils(androidApplication()) }
    single { providePreferenceUtils(androidApplication()) }
}

/**
 * Provide Network utils instance
 */
private fun provideNetworkUtils(applicationContext: Application): NetworkUtils {
    return NetworkUtils(applicationContext)

}

/**
 * Provide preference utils instance
 */
private fun providePreferenceUtils(applicationContext: Application): PreferenceUtils {
    return PreferenceUtils(applicationContext, Gson())

}

