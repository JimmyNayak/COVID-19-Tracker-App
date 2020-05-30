package com.covidtracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.covidtracker.api.response.countries.CountryModel
import kotlinx.coroutines.Dispatchers

/**
 * Created on 26/05/20.
 */
class UserDetailsViewModel : BaseViewModel() {

    /**
     * Validate user data
     */

    internal fun validateUserDetails(fullName: String, country: String): Boolean {

        return when {
            fullName.isEmpty() -> {
                errorMessage.value = "Please enter your full name."
                return false
            }

            country.isEmpty() -> {
                errorMessage.value = "Please select country from the list."
                return false
            }

            else -> true
        }

    }

    /**
     * Execute login request if data is valid
     */
   internal fun getCountryList(): LiveData<List<CountryModel>> {

        if (!mNetworkUtils.isConnected()) {
            errorMessage.value = "Please check your internet connection."

        }

        isLoading.value = true

        return liveData(Dispatchers.IO) {

            try {
                emit(mAPiManager.getCountryList())
            } catch (exception: Exception) {
                errorMessage.postValue(exception.message ?: "Something went wrong")
            }

            isLoading.postValue(false)
        }

    }

}