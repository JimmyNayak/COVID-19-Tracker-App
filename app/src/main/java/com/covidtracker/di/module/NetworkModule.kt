package com.covidtracker.di.module

import android.app.Application
import com.covidtracker.api.APIManger
import com.covidtracker.api.RetrofitBuilder
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * Created on 28/05/20.
 */
// Provide all network api request model here
val networkModule = module {
    single { provideAPIManager(androidApplication()) }
}

/**
 * Provide API manager
 */
private fun provideAPIManager(applicationContext: Application): APIManger {
    return APIManger(RetrofitBuilder(applicationContext).getRetrofit())
}

