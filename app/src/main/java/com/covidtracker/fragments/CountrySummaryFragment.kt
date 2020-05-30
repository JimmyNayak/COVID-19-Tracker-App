package com.covidtracker.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.covidtracker.R
import com.covidtracker.R.string
import com.covidtracker.activities.HomeActivity
import com.covidtracker.adapter.CountrySummaryAdapter
import com.covidtracker.adapter.GlobalSummaryAdapter
import com.covidtracker.api.response.summary.CountrySummaryModel
import com.covidtracker.base.CovidTrackerBaseActivity
import com.covidtracker.base.CovidTrackerBaseFragment
import com.covidtracker.model.GlobalSummaryDetailsModel
import kotlinx.android.synthetic.main.fragment_country_summary.rv_country_summary_list
import kotlinx.android.synthetic.main.fragment_global_summary.rv_global_summary_list

class CountrySummaryFragment : CovidTrackerBaseFragment() {

    private lateinit var mCountrySummaryAdapter: CountrySummaryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_summary, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initComponent()
    }

    override fun initComponent() {

        (activity as CovidTrackerBaseActivity).supportActionBar?.title = getString(string.title_country_state)

        setUpRecyclerView()

        initViewModel()


    }

    override fun initViewModel() {

        (activity as HomeActivity).mHomeViewModel.isDataLoaded.observe(activity as HomeActivity, Observer {

            (activity as HomeActivity).mSummaryResponseModel?.countries?.let {

                mCountrySummaryAdapter.mSummaryList = it as ArrayList<CountrySummaryModel>
                mCountrySummaryAdapter.notifyDataSetChanged()

            }

        })

    }

    /**
     * Set up recycler view with resources
     */
    private fun setUpRecyclerView() {
        rv_country_summary_list.layoutManager = LinearLayoutManager(activity)

        mCountrySummaryAdapter = CountrySummaryAdapter(activity as CovidTrackerBaseActivity)

        rv_country_summary_list.adapter = mCountrySummaryAdapter
    }
}