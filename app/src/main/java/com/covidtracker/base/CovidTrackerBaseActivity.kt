package com.covidtracker.base

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.covidtracker.utils.PreferenceUtils
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.koindemo.dialogs.CustomProgressDialog
import org.koin.android.ext.android.inject

/**
 * Created on 26/05/20.
 */
abstract class CovidTrackerBaseActivity : AppCompatActivity() {

    private lateinit var mProgressBar: CustomProgressDialog

    private val mInputMethodManager: InputMethodManager by inject()

    internal val mPreferenceUtils: PreferenceUtils by inject()

    internal val mGson: Gson by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mProgressBar = CustomProgressDialog(this)
    }

    /**
     * Show progress bar
     */
    internal fun showLoader() {

        if (::mProgressBar.isInitialized) {
            mProgressBar.show()
        }

    }

    /**
     * Hide progress bar
     */
    internal fun hideLoader() {
        if (::mProgressBar.isInitialized && mProgressBar.isShowing) {
            mProgressBar.hide()
        }
    }

    /**
     * Show error message
     */
    internal fun showError(message: String) {

        Snackbar.make(findViewById(android.R.id.content), message, BaseTransientBottomBar.LENGTH_SHORT).show()

    }

    /**
     * Hide keyboard
     */
    internal fun hideKeyboard(view: View?) {

        view?.let {
            mInputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }

    }

    abstract fun initComponent()

    abstract fun initViewModel()


}