package com.covidtracker.base

import android.view.View
import androidx.fragment.app.Fragment
import com.covidtracker.utils.PreferenceUtils
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import org.koin.android.ext.android.inject

/**
 * Created on 29/05/20.
 */
abstract class CovidTrackerBaseFragment : Fragment() {

    internal val mPreferenceUtils: PreferenceUtils by inject()

    internal val mGson: Gson by inject()

    /**
     * Show progress bar
     */
    internal fun showLoader() {
        (activity as CovidTrackerBaseActivity).showLoader()

    }

    /**
     * Hide progress bar
     */
    internal fun hideLoader() {
        (activity as CovidTrackerBaseActivity).hideLoader()
    }

    /**
     * Show error message
     */
    internal fun showError(message: String) {

        (activity as CovidTrackerBaseActivity).showError(message)

    }

    /**
     * Hide keyboard
     */
    internal fun hideKeyboard(view: View?) {

        view?.let {
            (activity as CovidTrackerBaseActivity).hideKeyboard(it)
        }

    }


    abstract fun initComponent()

    abstract fun initViewModel()
}