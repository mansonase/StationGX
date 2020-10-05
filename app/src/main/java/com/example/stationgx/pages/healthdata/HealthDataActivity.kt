package com.example.stationgx.pages.healthdata

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.stationgx.R
import com.example.stationgx.pages.BaseActivity
import com.example.stationgx.pages.mainbaseactivity.homefragment.HomeActivity
import kotlinx.android.synthetic.main.main_healthdata.*


class HealthDataActivity:BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_healthdata)

        btn_healthdata.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_healthdata->{
                val intent=Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }
}