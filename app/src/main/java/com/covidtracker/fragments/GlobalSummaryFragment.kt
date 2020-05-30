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
import com.covidtracker.adapter.GlobalSummaryAdapter
import com.covidtracker.api.response.summary.SummaryResponseModel
import com.covidtracker.base.CovidTrackerBaseActivity
import com.covidtracker.base.CovidTrackerBaseFragment
import com.covidtracker.model.GlobalSummaryDetailsModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.fragment_global_summary.rv_global_summary_list


class GlobalSummaryFragment : CovidTrackerBaseFragment() {

    private lateinit var mGlobalSummaryAdapter: GlobalSummaryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_global_summary, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initComponent()
    }

    override fun initComponent() {

        (activity as CovidTrackerBaseActivity).supportActionBar?.title = getString(string.title_global_states)

        setUpRecyclerView()

        initViewModel()
    }

    override fun initViewModel() {

        (activity as HomeActivity).mHomeViewModel.isDataLoaded.observe(activity as HomeActivity, Observer {
            mGlobalSummaryAdapter.mSummaryList = getSummaryList()

            mGlobalSummaryAdapter.notifyDataSetChanged()

        })

    }

    /**
     * Set up recycler view with resources
     */
    private fun setUpRecyclerView() {
        rv_global_summary_list.layoutManager = LinearLayoutManager(activity)

        mGlobalSummaryAdapter = GlobalSummaryAdapter(activity as CovidTrackerBaseActivity)

        rv_global_summary_list.adapter = mGlobalSummaryAdapter
    }

    /**
     * Fill the global summary model details and return the list
     */
    private fun getSummaryList(): ArrayList<GlobalSummaryDetailsModel> {

        val mSummaryList = ArrayList<GlobalSummaryDetailsModel>()

        (activity as HomeActivity).mSummaryResponseModel?.global?.let {

            mSummaryList.add(GlobalSummaryDetailsModel("New Confirmed Cases", it.newConfirmed.toString()))
            mSummaryList.add(GlobalSummaryDetailsModel("Total Confirmed Cases", it.totalConfirmed.toString()))
            mSummaryList.add(GlobalSummaryDetailsModel("New Deaths", it.newDeaths.toString()))
            mSummaryList.add(GlobalSummaryDetailsModel("Total Deaths", it.totalDeaths.toString()))
            mSummaryList.add(GlobalSummaryDetailsModel("New Recovered", it.newRecovered.toString()))
            mSummaryList.add(GlobalSummaryDetailsModel("Total Recovered", it.totalRecovered.toString()))

        }

        return mSummaryList

    }

}