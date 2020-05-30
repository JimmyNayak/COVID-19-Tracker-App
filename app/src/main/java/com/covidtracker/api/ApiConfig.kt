package com.covidtracker.api

/**
 * Created on 26/05/20.
 */
object ApiConfig {

    const val BASE_URL = "https://api.covid19api.com/"

    //Response cache size for OkHTTP - 10 MB
    const val MAX_HTTP_CACHE_SIZE = 10 * 1024 * 1024.toLong()

    // API header configuration
    object APIHeader {

        const val API_COUNTRIES = "countries"
        const val API_SUMMARY = "summary"

    }

    // Time configuration constants
    object TimeConfig {

        const val CONNECT = 15
        const val READ = 30
        const val WRITE = 30
    }


}