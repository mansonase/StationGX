package com.example.stationgx.pages.signinup.signinfragment

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.example.stationgx.R

class SignInCustomAlertDialog(context: Context): Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bg_signin_custom_alert_dialog)
    }
}