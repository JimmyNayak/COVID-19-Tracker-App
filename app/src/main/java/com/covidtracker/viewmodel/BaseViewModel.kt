package com.covidtracker.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.covidtracker.api.APIManger
import com.covidtracker.utils.NetworkUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.java.KoinJavaComponent.inject

/**
 * Created on 26/05/20.
 */
open class BaseViewModel : ViewModel() {

    protected val mAPiManager: APIManger by inject(APIManger::class.java)

    protected val mNetworkUtils: NetworkUtils by inject(NetworkUtils::class.java)

    protected val mViewModelJob = SupervisorJob()

    protected val mScope = CoroutineScope(Dispatchers.IO+mViewModelJob)

    val isLoading = MutableLiveData<Boolean>()

    val errorMessage = MutableLiveData<String>()



}