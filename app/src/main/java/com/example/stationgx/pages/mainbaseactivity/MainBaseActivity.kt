package com.example.stationgx.pages.mainbaseactivity

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import com.example.stationgx.R
import com.example.stationgx.pages.BaseActivity
import javax.inject.Inject

class MainBaseActivity: BaseActivity() {

    @Inject
    lateinit var shared:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_base)

        val isSaved= shared.edit().putString("mainbaseact","what is that").commit()

        if (isSaved)
            Log.d("main base","got return true")
    }


}