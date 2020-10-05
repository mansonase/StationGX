package com.example.stationgx.pages.phone

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.stationgx.R
import com.example.stationgx.pages.BaseActivity
import com.example.stationgx.pages.mainbaseactivity.homefragment.HomeActivity
import kotlinx.android.synthetic.main.main_phone.*

class PhoneActivity:BaseActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_phone)
        btn_phone.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_phone->{
                val intent=Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }
}