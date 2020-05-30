package com.covidtracker.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.covidtracker.R.layout
import com.covidtracker.base.CovidTrackerBaseActivity
import com.covidtracker.utils.AppConstant

class SplashActivity : CovidTrackerBaseActivity() {

    private lateinit var mSplashHandler: Handler

    private lateinit var mSplashRunnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_splash)

        initComponent()
    }

    override fun initComponent() {

        mSplashRunnable = Runnable {

            val mIntent = if (mPreferenceUtils.getBoolean(AppConstant.PreferenceKey.PREF_IS_LOGIN)) {
                Intent(this, HomeActivity::class.java)

            } else {
                Intent(this, UserDetailsActivity::class.java)
            }


            mIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(mIntent)
        }

        mSplashHandler = Handler()

        mSplashHandler.postDelayed(mSplashRunnable, 3000)


    }

    override fun initViewModel() {

    }

    override fun onDestroy() {
        super.onDestroy()
        mSplashHandler.removeCallbacks(mSplashRunnable)
    }
}
