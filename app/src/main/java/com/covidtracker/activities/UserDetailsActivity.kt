package com.covidtracker.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.covidtracker.R
import com.covidtracker.R.string
import com.covidtracker.adapter.CountryListAdapter
import com.covidtracker.api.response.countries.CountryModel
import com.covidtracker.base.CovidTrackerBaseActivity
import com.covidtracker.model.UserModel
import com.covidtracker.utils.AppConstant
import com.covidtracker.viewmodel.UserDetailsViewModel
import kotlinx.android.synthetic.main.activity_user_details.btn_start
import kotlinx.android.synthetic.main.activity_user_details.et_user_name
import kotlinx.android.synthetic.main.activity_user_details.tv_countries

class UserDetailsActivity : CovidTrackerBaseActivity(), OnClickListener, OnItemClickListener {

    private lateinit var mUserDetailsViewModel: UserDetailsViewModel

    private lateinit var mCountryListAdapter: CountryListAdapter

    private var mSelectedCountryModel: CountryModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        initComponent()
    }

    override fun initComponent() {

        supportActionBar?.title = getString(string.title_user_info)

        initViewModel()

        btn_start.setOnClickListener(this)

        getCountryListFromServer()


    }


    override fun initViewModel() {

        mUserDetailsViewModel = ViewModelProviders.of(this).get(UserDetailsViewModel::class.java)

        mUserDetailsViewModel.isLoading.observe(this, Observer {
            when {
                it -> showLoader()
                else -> hideLoader()
            }
        })

        mUserDetailsViewModel.errorMessage.observe(this, Observer {
            showError(it)

        })

    }

    override fun onClick(v: View?) {
        hideKeyboard(v)
        when (v?.id) {
            R.id.btn_start -> {

                if (mUserDetailsViewModel.validateUserDetails(
                        et_user_name.text.toString(),
                        tv_countries.text.toString()
                    )
                ) {
                    mSelectedCountryModel?.let {

                        mPreferenceUtils.saveData(
                            AppConstant.PreferenceKey.PREF_USER_DATA,
                            UserModel(et_user_name.text.toString(), it)
                        )

                        mPreferenceUtils.saveData(AppConstant.PreferenceKey.PREF_IS_LOGIN, true)

                    }

                    val mIntent = Intent(this, HomeActivity::class.java)
                    mIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(mIntent)


                }

            }
        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        mSelectedCountryModel = mCountryListAdapter.getItem(position)
        tv_countries.setText(mCountryListAdapter.getItem(position).country)
    }

    /**
     * Set country list adapter
     */
    private fun setCountryAdapter(countryList: ArrayList<CountryModel>) {
        mCountryListAdapter = CountryListAdapter(this, countryList)
        tv_countries.setAdapter(mCountryListAdapter)
        tv_countries.threshold = 2
        tv_countries.onItemClickListener = this
//        tv_countries.showDropDown()
    }

    /**
     * Get country list from server
     */
    private fun getCountryListFromServer() {
        mUserDetailsViewModel.getCountryList().observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                mPreferenceUtils.saveData(AppConstant.PreferenceKey.PREF_COUNTRIES, it)
                setCountryAdapter(it as ArrayList<CountryModel>)


            }

        })
    }
}
