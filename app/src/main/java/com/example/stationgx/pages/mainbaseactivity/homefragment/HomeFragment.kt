package com.example.stationgx.pages.mainbaseactivity.homefragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.stationgx.R
import com.example.stationgx.pages.BaseFragment
import com.example.stationgx.pages.healthdata.HealthDataActivity
import com.example.stationgx.pages.manuelinput.ManualInputActivity
import com.example.stationgx.pages.measurement.MeasurementActivity
import com.example.stationgx.pages.medication.MedicationActivity
import com.example.stationgx.pages.myprofile.MyProfileActivity
import com.example.stationgx.pages.phone.PhoneActivity
import com.example.stationgx.pages.telehealth.TeleHealthActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.*
import javax.inject.Inject

class HomeFragment:BaseFragment(),HomeFragmentContract.View,View.OnClickListener{

    @Inject
    lateinit var presenter:HomeFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view=inflater.inflate(R.layout.fragment_home,container,false)

        view.img_telehealth .setOnClickListener(this)
        view.img_manualinput.setOnClickListener(this)
        view.img_medication .setOnClickListener(this)
        view.img_measurement.setOnClickListener(this)
        view.img_music      .setOnClickListener(this)
        view.img_profile    .setOnClickListener(this)
        view.img_health_data.setOnClickListener(this)
        view.img_phone      .setOnClickListener(this)
        view.img_date       .setOnClickListener(this)


        return view
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.img_telehealth->{
                //todo need to include a bundle factor to indicate which fragment we need to return to
                val intent=Intent(this.context,TeleHealthActivity::class.java)
                startActivity(intent)
            }
            R.id.img_manualinput->{
                val intent=Intent(this.context,ManualInputActivity::class.java)
                startActivity(intent)
            }
            R.id.img_medication->{
                val intent=Intent(this.context,MedicationActivity::class.java)
                startActivity(intent)
            }
            R.id.img_measurement->{
                val intent=Intent(this.context,MeasurementActivity::class.java)
                startActivity(intent)
            }
            R.id.img_music->{
                //todo implement music app?
            }
            R.id.img_profile->{
                //todo implement MyProfile?
            }
            R.id.img_health_data->{
                val intent=Intent(this.context,HealthDataActivity::class.java)
                startActivity(intent)
            }
            R.id.img_phone->{
                val intent=Intent(this.context,PhoneActivity::class.java)
                startActivity(intent)
            }
            R.id.img_date->{
            }
            else->{
            }
        }
    }

    override fun showDate(current: Date) {
        //TODO("Not yet implemented")
    }

    override fun showLastDocumenting(current: Date) {
        //TODO("Not yet implemented")
    }

    override fun showNotificationRedPoint(numbers: Int) {
        //TODO("Not yet implemented")
    }

    override fun showName(name: String) {
        //TODO("Not yet implemented")
    }

    override fun showLocation(location: String) {
        //TODO("Not yet implemented")
    }
}