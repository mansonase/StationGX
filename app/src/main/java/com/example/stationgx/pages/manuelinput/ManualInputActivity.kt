package com.example.stationgx.pages.manuelinput

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
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
        entrance_temperature.setOnClickListener(this)
        entrance_oxygenation.setOnClickListener(this)
        entrance_blood_sugar.setOnClickListener(this)
        entrance_spirometer.setOnClickListener(this)
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
            R.id.entrance_blood_pressure -> {
                entrance_blood_pressure.setTextColor(resources.getColor(R.color.word_blue, null))
                last_blood_pressure.setTextColor(resources.getColor(R.color.word_blue, null))
                quote_text.text = ""
                quote_author.text = ""
                animation_manualinput.visibility = View.VISIBLE
                animation_manualinput.setAnimation("mi_bloddpressure.json")
                animation_manualinput.repeatCount = 1
                animation_manualinput.playAnimation()
                animation_manualinput.addAnimatorListener(object : Animator.AnimatorListener{
                    override fun onAnimationStart(animation: Animator?) {
                        //TODO("Not yet implemented")
                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        val intent=Intent(this@ManualInputActivity,BloodPressureActivity::class.java)
                        startActivity(intent)
                    }

                    override fun onAnimationCancel(animation: Animator?) {
                        //TODO("Not yet implemented")
                    }

                    override fun onAnimationRepeat(animation: Animator?) {
                        //TODO("Not yet implemented")
                    }
                })
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
                entrance_temperature.setTextColor(resources.getColor(R.color.word_blue,null))
                last_temperature.setTextColor(resources.getColor(R.color.word_blue,null))
                quote_text.text=""
                quote_author.text=""
                animation_manualinput.visibility=View.VISIBLE
                animation_manualinput.setAnimation("mi_temperature.json")
                animation_manualinput.repeatCount=1
                animation_manualinput.playAnimation()
            }
            R.id.entrance_oxygenation->{
                entrance_oxygenation.setTextColor(resources.getColor(R.color.word_blue,null))
                last_oxygenation.setTextColor(resources.getColor(R.color.word_blue,null))
                quote_text.text=""
                quote_author.text=""
                animation_manualinput.visibility=View.VISIBLE
                animation_manualinput.setAnimation("mi_oxygenation.json")
                animation_manualinput.repeatCount=1
                animation_manualinput.playAnimation()
            }
            R.id.entrance_blood_sugar->{
                entrance_blood_sugar.setTextColor(resources.getColor(R.color.word_blue,null))
                last_blood_sugar.setTextColor(resources.getColor(R.color.word_blue,null))
                quote_text.text=""
                quote_author.text=""
                animation_manualinput.visibility=View.VISIBLE
                animation_manualinput.setAnimation("mi_bloodsugar.json")
                animation_manualinput.repeatCount=1
                animation_manualinput.playAnimation()
            }
            R.id.entrance_spirometer->{
                entrance_spirometer.setTextColor(resources.getColor(R.color.word_blue,null))
                last_spirometer.setTextColor(resources.getColor(R.color.word_blue,null))
                quote_text.text=""
                quote_author.text=""
                animation_manualinput.visibility=View.VISIBLE
                animation_manualinput.setAnimation("mi_spirometer.json")
                animation_manualinput.repeatCount=1
                animation_manualinput.playAnimation()
            }
            R.id.entrance_others->{
                entrance_others.setTextColor(resources.getColor(R.color.word_blue,null))
                last_others.setTextColor(resources.getColor(R.color.word_blue,null))
                quote_text.text= ""
                quote_author.text=""
                animation_manualinput.visibility=View.VISIBLE
                animation_manualinput.setAnimation("mi_others.json")
                animation_manualinput.repeatCount=1
                animation_manualinput.playAnimation()
            }
        }
    }
}