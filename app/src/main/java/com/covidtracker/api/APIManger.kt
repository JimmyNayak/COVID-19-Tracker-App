package com.covidtracker.api

import com.covidtracker.api.response.countries.CountryModel
import com.covidtracker.api.response.summary.SummaryResponseModel

/**
 * Created on 26/05/20.
 */
class APIManger constructor(private val mAPi: API) {

    // Get country list
    suspend fun getCountryList(): List<CountryModel> {
        return mAPi.getCountries()
    }

    // Get corona virus summary list
    suspend fun getCoronaSummary(): SummaryResponseModel {
        return mAPi.getCoronaSummary()
    }

}