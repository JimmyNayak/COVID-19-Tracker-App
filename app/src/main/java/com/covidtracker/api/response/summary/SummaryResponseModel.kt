package com.covidtracker.api.response.summary

import android.provider.Settings.Global
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


/**
 * Created on 29/05/20.
 */

/**
 * Global summary response model
 */
data class SummaryResponseModel(

    @SerializedName("Global")
    @Expose
    var global: GlobalSummaryModel?,

    @SerializedName("Countries")
    @Expose
    var countries: List<CountrySummaryModel>?,

    @SerializedName("Date")
    @Expose
    var date: String?

)

/**
 * Country summary model
 */
data class CountrySummaryModel(

    @SerializedName("Country")
    @Expose
    val country: String?,

    @SerializedName("CountryCode")
    @Expose
    val countryCode: String?,

    @SerializedName("Slug")
    @Expose
    val slug: String?,

    @SerializedName("NewConfirmed")
    @Expose
    val newConfirmed: Int = 0,

    @SerializedName("TotalConfirmed")
    @Expose
    val totalConfirmed: Int = 0,

    @SerializedName("NewDeaths")
    @Expose
    val newDeaths: Int = 0,

    @SerializedName("TotalDeaths")
    @Expose
    val totalDeaths: Int = 0,

    @SerializedName("NewRecovered")
    @Expose
    val newRecovered: Int = 0,

    @SerializedName("TotalRecovered")
    @Expose
    val totalRecovered: Int = 0,

    @SerializedName("Date")
    @Expose
    val date: String?

)

/**
 * Global summary model
 */

class GlobalSummaryModel(

    @SerializedName("NewConfirmed")
    @Expose
    var newConfirmed: Int = 0,

    @SerializedName("TotalConfirmed")
    @Expose
    var totalConfirmed: Int = 0,

    @SerializedName("NewDeaths")
    @Expose
    var newDeaths: Int = 0,

    @SerializedName("TotalDeaths")
    @Expose
    var totalDeaths: Int = 0,

    @SerializedName("NewRecovered")
    @Expose
    var newRecovered: Int = 0,

    @SerializedName("TotalRecovered")
    @Expose
    var totalRecovered: Int = 0
)
