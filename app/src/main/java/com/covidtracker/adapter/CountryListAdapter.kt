package com.covidtracker.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.covidtracker.R
import com.covidtracker.api.response.countries.CountryModel
import kotlinx.android.synthetic.main.row_country.view.tv_country_name

/**
 * Created on 28/05/20.
 */
class CountryListAdapter(private var mContext: Context,private var mCountryList : ArrayList<CountryModel>) : BaseAdapter(), Filterable {

    private var mOriginalValues = ArrayList<CountryModel>()
    private var mFilter: Filter? = null

    init {
        mOriginalValues = mCountryList
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val callsItemViewHolder: CallsItemViewHolder
        val callView: View

        if (convertView == null) {

            callView = LayoutInflater.from(mContext)
                .inflate(R.layout.row_country, parent, false)
            callsItemViewHolder = CallsItemViewHolder(callView)

            callView.tag = callsItemViewHolder
        } else {
            callView = convertView
            callsItemViewHolder = callView.tag as CallsItemViewHolder
        }

        callsItemViewHolder.tvCountryName.text = mCountryList[position].country

        return callView

    }

    override fun getItem(position: Int): CountryModel {
        return mCountryList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mCountryList.size
    }


    override fun getFilter(): Filter {
        if (mFilter == null) {
            mFilter = object : Filter() {
                override fun performFiltering(constraint: CharSequence?): FilterResults {

                    val results = FilterResults()

                    if (mOriginalValues == null) {
                        mOriginalValues = ArrayList(mCountryList)
                    }

                    if (constraint == null || constraint.isEmpty()) {

                        val list: ArrayList<CountryModel> = ArrayList(mOriginalValues)
                        results.values = list
                        results.count = list.size

                    } else {
                        val prefixString: String = constraint.toString().toLowerCase()
                        val newValues =
                            mOriginalValues.filter {
                                it.country?.toLowerCase()?.contains(prefixString) == true
                            }
                        results.values = newValues
                        results.count = newValues.size
                    }

                    return results

                }

                override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                    mCountryList = if (results?.values != null) {
                        results.values as ArrayList<CountryModel>
                    } else {
                        arrayListOf()
                    }
                    if (results?.count!! > 0) {
                        notifyDataSetChanged()
                    } else {
                        notifyDataSetInvalidated()
                    }
                }

            }
        }
        return mFilter!!

    }

    class CallsItemViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        val tvCountryName: AppCompatTextView = view.tv_country_name

    }

}