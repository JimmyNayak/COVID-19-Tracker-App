package com.covidtracker.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import com.covidtracker.R
import com.covidtracker.R.string
import com.covidtracker.activities.HomeActivity
import com.covidtracker.adapter.CountryListAdapter
import com.covidtracker.api.response.countries.CountryModel
import com.covidtracker.base.CovidTrackerBaseActivity
import com.covidtracker.base.CovidTrackerBaseFragment
import com.covidtracker.model.UserModel
import com.covidtracker.utils.AppConstant
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_user_details.tv_countries
import kotlinx.android.synthetic.main.fragment_user_profile.et_user_profile_country
import kotlinx.android.synthetic.main.fragment_user_profile.et_user_profile_name
import kotlinx.android.synthetic.main.fragment_user_profile.tv_profile_countries
import java.lang.reflect.Type

class UserProfileFragment : CovidTrackerBaseFragment(), OnItemClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initComponent()
    }

    override fun initComponent() {

        (activity as CovidTrackerBaseActivity).supportActionBar?.title = getString(string.title_profile)

        setUserData()


    }

    override fun initViewModel() {

    }

    /**
     * Set country list adapter
     */
    private fun setUserData() {

        if (mPreferenceUtils.isKeyAvailable(AppConstant.PreferenceKey.PREF_USER_DATA)) {
            val mUserModel =
                mPreferenceUtils.getObject(AppConstant.PreferenceKey.PREF_USER_DATA, UserModel::class.java)

            et_user_profile_name.setText(mUserModel?.userName.toString())

            et_user_profile_country.setText(mUserModel?.userCountry?.country.toString())
        }

//        if (mPreferenceUtils.isKeyAvailable(AppConstant.PreferenceKey.PREF_COUNTRIES)) {
//
//            tv_profile_countries.visibility = View.VISIBLE
//
//            val type: Type = object : TypeToken<List<CountryModel?>?>() {}.type
//            val mCountryList: List<CountryModel> =
//                mGson.fromJson(mPreferenceUtils.getString(AppConstant.PreferenceKey.PREF_COUNTRIES), type)
//
//            tv_profile_countries.setAdapter(CountryListAdapter(activity as HomeActivity,
//                mCountryList as ArrayList<CountryModel>
//            ))
//            tv_profile_countries.threshold = 2
//            tv_profile_countries.onItemClickListener = this
//
//        } else {
//            tv_profile_countries.visibility = View.GONE
//        }

//        tv_countries.showDropDown()
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

}