package com.example.stationgx.pages.manuelinput.bloodpressure

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.stationgx.R
import com.example.stationgx.base.BaseActivity
import com.example.stationgx.pages.manuelinput.ManualInputActivity
import kotlinx.android.synthetic.main.dialog_blood_pressure_input.*
import kotlinx.android.synthetic.main.main_blood_pressure.*

class BloodPressureActivity:BaseActivity(),View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_blood_pressure)

        blood_pressure_start_input.setOnClickListener(this)
        blood_pressure_back.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.blood_pressure_start_input->{
                Log.d("testing","clicked input")
                val dialog=Dialog(this)
                dialog.setContentView(R.layout.dialog_blood_pressure_input)
                dialog.blood_pressure_dialog_clear.setOnClickListener { dialog.dismiss() }
                dialog.show()
            }
            R.id.blood_pressure_back->{
                Log.d("testing","clicked in blood pressure")
                val intent=Intent(this,ManualInputActivity::class.java)
                startActivity(intent)
            }
        }
    }
}