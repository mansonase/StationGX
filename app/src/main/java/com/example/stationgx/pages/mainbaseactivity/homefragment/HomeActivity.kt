package com.example.stationgx.pages.mainbaseactivity.homefragment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.stationgx.R
import com.example.stationgx.pages.BaseActivity
import com.example.stationgx.pages.healthdata.HealthDataActivity
import com.example.stationgx.pages.manuelinput.ManualInputActivity
import com.example.stationgx.pages.measurement.MeasurementActivity
import com.example.stationgx.pages.medication.MedicationActivity
import com.example.stationgx.pages.phone.PhoneActivity
import com.example.stationgx.pages.telehealth.TeleHealthActivity
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


class HomeActivity:BaseActivity(), View.OnClickListener{

    @Inject
    lateinit var shared:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        //AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_home)



        /*
        img_profile.     setOnClickListener(this)
        img_health_data. setOnClickListener(this)
        img_phone.       setOnClickListener(this)
        img_date.        setOnClickListener(this)
        //img_telehealth.  setOnClickListener(this)
        img_manualinput. setOnClickListener(this)
        img_medication.  setOnClickListener(this)
        img_measurement. setOnClickListener(this)

         */

        if (shared!=null){
            shared.edit().putString("numbers","12333").commit()
        }
    }

    override fun onClick(v: View?) {

        /*
        when(v?.id){
            R.id.img_profile->{
                val testString=shared.getString("numbers","no numbers")
                Log.d("HomeActivity", " result is : $testString")
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
            R.id.frame_telehealth->{
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

         */
    }
}