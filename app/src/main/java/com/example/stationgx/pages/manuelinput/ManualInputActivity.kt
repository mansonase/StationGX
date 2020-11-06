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
        entrance_weight.setOnClickListener(this)
        entrance_others.setOnClickListener(this)
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
                //val intent=Intent(this,BloodPressureActivity::class.java)
                //startActivity(intent)
                entrance_blood_pressure.setTextColor(resources.getColor(R.color.word_blue,null))
                last_blood_pressure.setTextColor(resources.getColor(R.color.word_blue,null))
                quote_text.text=""
                quote_author.text=""
                animation_manualinput.visibility=View.VISIBLE
                animation_manualinput.setAnimation("mi_bloddpressure.json")
                animation_manualinput.repeatCount=1
                animation_manualinput.playAnimation()

            }
            R.id.entrance_weight->{
                entrance_weight.setTextColor(resources.getColor(R.color.word_blue,null))
                last_weight.setTextColor(resources.getColor(R.color.word_blue,null))
                quote_text.text=""
                quote_author.text=""
                animation_manualinput.visibility=View.VISIBLE
                animation_manualinput.setAnimation("mi_weight.json")
                animation_manualinput.repeatCount=1
                animation_manualinput.playAnimation()
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
                entrance_others.setTextColor(resources.getColor(R.color.word_blue,null))
                last_others.setTextColor(resources.getColor(R.color.word_blue,null))
                quote_text.text= null
                quote_author.text=null
                animation_manualinput.visibility=View.VISIBLE
                animation_manualinput.setAnimation("mi_others.json")
                animation_manualinput.repeatCount=1
                animation_manualinput.playAnimation()
            }
        }
    }
}