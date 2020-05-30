package com.covidtracker.activities

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.covidtracker.R
import com.covidtracker.api.response.countries.CountryModel
import com.covidtracker.api.response.summary.SummaryResponseModel
import com.covidtracker.base.CovidTrackerBaseActivity
import com.covidtracker.utils.AppConstant
import com.covidtracker.viewmodel.HomeViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_home.bn_home_navigation
import kotlinx.android.synthetic.main.activity_home.sr_refresh_data
import java.lang.reflect.Type

class HomeActivity : CovidTrackerBaseActivity(), OnRefreshListener {

    internal lateinit var mHomeViewModel: HomeViewModel

    internal var mSummaryResponseModel: SummaryResponseModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initComponent()
    }

    override fun initComponent() {

        initViewModel()

        setUpNavigation()

        sr_refresh_data.setOnRefreshListener(this)

        getSummaryDetails()

    }

    override fun initViewModel() {

        mHomeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        mHomeViewModel.isLoading.observe(this, Observer {
            when {
                it -> showLoader()
                else -> hideLoader()
            }
        })

        mHomeViewModel.errorMessage.observe(this, Observer {
            showError(it)

        })


    }

    /**
     * Set up navigation controller
     */
    private fun setUpNavigation() {

        // Finding the Navigation Controller
        val navController = findNavController(R.id.fg_container)

        // Setting Navigation Controller with the BottomNavigationView
        bn_home_navigation.setupWithNavController(navController)

    }

    /**
     * Get summary details from server
     */
    private fun getSummaryDetails() {

        mHomeViewModel.getCoronaSummary().observe(this, Observer {
            if (it != null) {
                mSummaryResponseModel = it

            }
        })
    }

    override fun onRefresh() {
        sr_refresh_data.isRefreshing = false
        getSummaryDetails()

    }

}
