package com.example.stationgx.pages.medication

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.stationgx.R
import com.example.stationgx.pages.BaseActivity
import com.example.stationgx.pages.mainbaseactivity.MainBaseActivity
import com.example.stationgx.pages.mainbaseactivity.homefragment.HomeActivity
import kotlinx.android.synthetic.main.main_medication.*

class MedicationActivity:BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_medication)
        btn_medication.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_medication-> {
                val intent = Intent(this, MainBaseActivity::class.java)
                startActivity(intent)
            }
        }
    }
}