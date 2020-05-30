package com.covidtracker.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.covidtracker.R
import com.covidtracker.adapter.GlobalSummaryAdapter.GlobalSummaryViewHolder

import com.covidtracker.model.GlobalSummaryDetailsModel
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.row_global_summary.view.tv_summary_title
import kotlinx.android.synthetic.main.row_global_summary.view.tv_summary_value

/**
 * Created on 29/05/20.
 */
class GlobalSummaryAdapter constructor(
    private val mContext: Context
) :
    RecyclerView.Adapter<GlobalSummaryViewHolder>() {


    internal var mSummaryList = ArrayList<GlobalSummaryDetailsModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GlobalSummaryViewHolder {
        val mView = LayoutInflater.from(mContext).inflate(R.layout.row_global_summary, parent, false)
        return GlobalSummaryViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return mSummaryList.size
    }

    override fun onBindViewHolder(holder: GlobalSummaryViewHolder, position: Int) {

        holder.tvSummaryTitle.text = mSummaryList[position].summaryTitle

        holder.tvSummaryValue.text = mSummaryList[position].SummaryValue
    }

    /**
     * Global summary view holder
     */
    class GlobalSummaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvSummaryTitle: MaterialTextView = itemView.tv_summary_title
        val tvSummaryValue: MaterialTextView = itemView.tv_summary_value

    }
}