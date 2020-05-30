package com.covidtracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.covidtracker.api.response.countries.CountryModel
import com.covidtracker.api.response.summary.SummaryResponseModel
import kotlinx.coroutines.Dispatchers

/**
 * Created on 26/05/20.
 */
class HomeViewModel : BaseViewModel() {

    internal var isDataLoaded = MutableLiveData<Boolean>()

    /**
     * Execute login request if data is valid
     */
    internal fun getCoronaSummary(): LiveData<SummaryResponseModel> {

        if (!mNetworkUtils.isConnected()) {
            errorMessage.value = "Please check your internet connection."

        }

        isLoading.value = true

        return liveData(Dispatchers.IO) {

            try {
                emit(mAPiManager.getCoronaSummary())
                isDataLoaded.postValue(true)
            } catch (exception: Exception) {
                isDataLoaded.postValue(false)
                errorMessage.postValue(exception.message ?: "Something went wrong")
            }

            isLoading.postValue(false)
        }

    }

}