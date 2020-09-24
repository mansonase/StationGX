package com.example.stationgx.pages.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.stationgx.R
import com.example.stationgx.pages.BaseActivity
import com.example.stationgx.pages.healthdata.HealthDataActivity
import com.example.stationgx.pages.manuelinput.ManualInputActivity
import com.example.stationgx.pages.measurement.MeasurementActivity
import com.example.stationgx.pages.medication.MedicationActivity
import com.example.stationgx.pages.myprofile.MyProfileActivity
import com.example.stationgx.pages.phone.PhoneActivity
import com.example.stationgx.pages.telehealth.TeleHealthActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import kotlinx.android.synthetic.main.main_home.*
import javax.inject.Inject


class HomeActivity:BaseActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_home)



        img_profile.     setOnClickListener(this)
        img_health_data. setOnClickListener(this)
        img_phone.       setOnClickListener(this)
        img_date.        setOnClickListener(this)
        img_telehealth.  setOnClickListener(this)
        img_manualinput. setOnClickListener(this)
        img_medication.  setOnClickListener(this)
        img_measurement. setOnClickListener(this)


    }

    override fun onClick(v: View?) {

        when(v?.id){
            R.id.img_profile->{
            }
            R.id.img_health_data->{
                val  intent=Intent(this,HealthDataActivity::class.java)
                startActivity(intent)
            }
            R.id.img_phone->{
                val intent=Intent(this,PhoneActivity::class.java)
                startActivity(intent)
            }
            R.id.img_date->{

            }
            R.id.img_telehealth->{
                val intent=Intent(this,TeleHealthActivity::class.java)
                startActivity(intent)
            }
            R.id.img_manualinput->{
                val intent=Intent(this,ManualInputActivity::class.java)
                startActivity(intent)
            }
            R.id.img_medication->{
                val intent=Intent(this,MedicationActivity::class.java)
                startActivity(intent)
            }
            R.id.img_measurement->{
                val intent=Intent(this,MeasurementActivity::class.java)
                startActivity(intent)
            }
        }
    }
}