package com.koindemo.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import com.covidtracker.R


/**
 * Created on 26/05/20.
 */
class CustomProgressDialog(context: Context) : Dialog(context) {


    override fun onCreate(savedInstanceState: Bundle?) {

        window?.requestFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.layput_progress_dialog)

        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }

}