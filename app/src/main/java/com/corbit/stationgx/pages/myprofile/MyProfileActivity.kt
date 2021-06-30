package com.corbit.stationgx.pages.myprofile

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.corbit.stationgx.R

class MyProfileActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}