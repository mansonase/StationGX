package com.example.stationgx.pages.manuelinput

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.stationgx.R
import com.example.stationgx.base.BaseActivity
import com.example.stationgx.pages.mainbaseactivity.MainBaseActivity
import com.example.stationgx.pages.manuelinput.bloodpressure.BloodPressureActivity
import kotlinx.android.synthetic.main.main_manualinput.*

class ManualInputActivity: BaseActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_manualinput)

        manualinput_back.setOnClickListener(this)
        entrance_blood_pressure.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.manualinput_back-> {
                val intent = Intent(this, MainBaseActivity::class.java)
                val bundle=Bundle()
                bundle.putInt(getString(R.string.fragment_indicator),1)
                intent.putExtra(getString(R.string.bundle_between_activities),bundle)
                startActivity(intent)
            }
            R.id.entrance_blood_pressure->{
                val intent=Intent(this,BloodPressureActivity::class.java)
                startActivity(intent)
            }
            R.id.entrance_weight->{

            }
            R.id.entrance_temperature->{

            }
            R.id.entrance_oxygenation->{

            }
            R.id.entrance_blood_sugar->{

            }
            R.id.entrance_spirometer->{

            }
            R.id.entrance_others->{

            }
        }
    }
}