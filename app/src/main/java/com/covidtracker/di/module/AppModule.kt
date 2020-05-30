package com.covidtracker.di.module

import android.app.Application
import android.content.Context
import android.view.inputmethod.InputMethodManager
import com.google.gson.Gson
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * Created on 26/05/20.
 */

// provide application related models here
val appModule = module {
    single { provideInputMethodManager(androidApplication()) }

    single { provideGson() }
}

/**
 * Get InputMethod instance
 */

private fun provideInputMethodManager(applicationContext: Application): InputMethodManager {

    return applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
}


/**
 * Get GSon instance
 */

private fun provideGson(): Gson {

    return Gson()
}
