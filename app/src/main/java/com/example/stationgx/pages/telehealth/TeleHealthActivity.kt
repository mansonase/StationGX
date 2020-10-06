package com.example.stationgx.pages.telehealth

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.stationgx.R
import com.example.stationgx.pages.BaseActivity
import com.example.stationgx.pages.mainbaseactivity.MainBaseActivity
import com.example.stationgx.pages.mainbaseactivity.homefragment.HomeActivity
import kotlinx.android.synthetic.main.main_telehealth.*

class TeleHealthActivity:BaseActivity(),View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_telehealth)
        btn_telehealth.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_telehealth->{
                val intent=Intent(this, MainBaseActivity::class.java)
                startActivity(intent)
            }
        }
    }
}