package com.covidtracker.api

import android.app.Application
import okhttp3.Cache
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit.SECONDS

/**
 * Created on 26/05/20.
 */
class RetrofitBuilder constructor(private var application: Application) {

    // Get retrofit instance
    fun getRetrofit(): API {
        return Retrofit.Builder()
            .baseUrl(ApiConfig.BASE_URL)
            .client(getHttpClientBuilder()?.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build() //Doesn't require the adapter
            .create(API::class.java)

    }

    // Get HTTP cline builder
    private fun getHttpClientBuilder(): Builder? {
        val cacheDir = File(application.cacheDir, "okhttp_cache")
        val cache = Cache(cacheDir, ApiConfig.MAX_HTTP_CACHE_SIZE)
        val builder: Builder = Builder()
            .connectTimeout(ApiConfig.TimeConfig.CONNECT.toLong(), SECONDS)
            .readTimeout(ApiConfig.TimeConfig.READ.toLong(), SECONDS)
            .writeTimeout(ApiConfig.TimeConfig.WRITE.toLong(), SECONDS)
//            .addInterceptor(HeaderInterceptor()) //add headers in HeaderInterceptor if needed and uncomment this. It will pass headers along with all outgoing requests
            .cache(cache)

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(BODY)
        builder.addInterceptor(loggingInterceptor)
        return builder
    }

}


