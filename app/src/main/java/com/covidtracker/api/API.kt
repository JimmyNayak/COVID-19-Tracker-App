package com.covidtracker.api

import com.covidtracker.api.ApiConfig.APIHeader
import com.covidtracker.api.response.countries.CountryModel
import com.covidtracker.api.response.summary.SummaryResponseModel
import retrofit2.http.GET

/**
 * Created on 26/05/20.
 */
interface API {

    // Get Country list
    @GET(APIHeader.API_COUNTRIES)
    suspend fun getCountries(): List<CountryModel>

    // Get summary list
    @GET(APIHeader.API_SUMMARY)
    suspend fun getCoronaSummary(): SummaryResponseModel


}