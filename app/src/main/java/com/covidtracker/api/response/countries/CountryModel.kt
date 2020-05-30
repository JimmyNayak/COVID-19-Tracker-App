package com.covidtracker.api.response.countries

import com.google.gson.annotations.SerializedName

data class CountryModel(

    @SerializedName("Country")
    val country: String?,

    @SerializedName("Slug")
    val slug: String? ,

    @SerializedName("ISO2")
    val iSO2: String?
)
