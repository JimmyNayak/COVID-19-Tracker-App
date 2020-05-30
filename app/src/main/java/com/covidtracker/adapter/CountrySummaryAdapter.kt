package com.covidtracker.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.covidtracker.R
import com.covidtracker.adapter.CountrySummaryAdapter.CountrySummaryViewHolder
import com.covidtracker.adapter.GlobalSummaryAdapter.GlobalSummaryViewHolder
import com.covidtracker.api.response.summary.CountrySummaryModel

import com.covidtracker.model.GlobalSummaryDetailsModel
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.row_country_summary.view.tv_confirmed_cases
import kotlinx.android.synthetic.main.row_country_summary.view.tv_country_name
import kotlinx.android.synthetic.main.row_country_summary.view.tv_deaths
import kotlinx.android.synthetic.main.row_country_summary.view.tv_recovered
import kotlinx.android.synthetic.main.row_global_summary.view.tv_summary_title
import kotlinx.android.synthetic.main.row_global_summary.view.tv_summary_value

/**
 * Created on 29/05/20.
 */
class CountrySummaryAdapter constructor(
    private val mContext: Context
) :
    RecyclerView.Adapter<CountrySummaryViewHolder>() {

    internal var mSummaryList = ArrayList<CountrySummaryModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountrySummaryViewHolder {
        val mView = LayoutInflater.from(mContext).inflate(R.layout.row_country_summary, parent, false)
        return CountrySummaryViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return mSummaryList.size
    }

    override fun onBindViewHolder(holder: CountrySummaryViewHolder, position: Int) {

        holder.tvCountryName.text = mSummaryList[position].country

        holder.tvConfirmedCases.text = mSummaryList[position].totalConfirmed.toString()

        holder.tvRecovered.text = mSummaryList[position].totalRecovered.toString()

        holder.tvDeaths.text = mSummaryList[position].totalDeaths.toString()
    }

    /**
     * Global summary view holder
     */
    class CountrySummaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvCountryName: MaterialTextView = itemView.tv_country_name
        val tvConfirmedCases: MaterialTextView = itemView.tv_confirmed_cases
        val tvRecovered: MaterialTextView = itemView.tv_recovered
        val tvDeaths: MaterialTextView = itemView.tv_deaths


    }
}