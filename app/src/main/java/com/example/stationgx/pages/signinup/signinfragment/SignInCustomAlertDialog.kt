package com.example.stationgx.pages.signinup.signinfragment

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.icu.text.CaseMap
import android.os.Bundle
import android.text.SpannableString
import android.widget.TextView
import com.example.stationgx.R
import kotlinx.android.synthetic.main.bg_signin_custom_alert_dialog.*

class SignInCustomAlertDialog(context: Context): Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bg_signin_custom_alert_dialog)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        btn_close.setOnClickListener {
            dismiss()
        }

        btn_negative.setOnClickListener {
            dismiss()
        }

        btn_positive.setOnClickListener {

        }
    }

    open fun setCustomTitle(title: String?) {
        tv_custom_dialog_title?.text = title
    }

    open fun setCustomMessage(message: SpannableString?) {
        tv_custom_dialog_message?.setText(message, TextView.BufferType.SPANNABLE)
    }
}