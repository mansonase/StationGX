package com.corbit.stationgx.pages.mainbaseactivity.homefragment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.corbit.stationgx.R
import com.corbit.stationgx.apis.dateapi.TimeIntent
import com.corbit.stationgx.data.prefs.SharedPreferencesHelper
import com.corbit.stationgx.base.BaseFragment
import com.corbit.stationgx.pages.healthdata.HealthDataActivity
import com.corbit.stationgx.pages.manuelinput.ManualInputActivity
import com.corbit.stationgx.pages.measurement.MeasurementActivity
import com.corbit.stationgx.pages.medication.MedicationActivity
import com.corbit.stationgx.pages.myprofile.MyProfileActivity
import com.corbit.stationgx.pages.phone.PhoneActivity
import com.corbit.stationgx.pages.telehealth.TeleHealthActivity
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.*
import javax.inject.Inject

class HomeFragment: BaseFragment(),HomeFragmentContract.View,View.OnClickListener{

    @Inject
    lateinit var presenter:HomeFragmentPresenter

    @Inject
    lateinit var sp:SharedPreferencesHelper

    val TAG = HomeFragment::class.simpleName

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view=inflater.inflate(R.layout.fragment_home,container,false)

        view.frame_telehealth .setOnClickListener(this)
        view.frame_manualinput.setOnClickListener(this)
        view.frame_medication .setOnClickListener(this)
        view.frame_measurement.setOnClickListener(this)
        view.frame_music      .setOnClickListener(this)
        view.img_home_profile .setOnClickListener(this)

        view.frame_health_data.setOnClickListener(this)
        view.frame_phone      .setOnClickListener(this)
        view.img_date       .setOnClickListener(this)

        Log.d("homeFra","gment: ${sp.getStringItem("testAAA","no data")}")
        return view
    }

    override fun onResume() {
        super.onResume()
        activity?.applicationContext?.registerReceiver(dateBroadcastReceiver,TimeIntent().intentFilter())
    }

    override fun onPause() {
        super.onPause()
        activity?.applicationContext?.unregisterReceiver(dateBroadcastReceiver)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.frame_telehealth->{
                //todo need to include a bundle factor to indicate which fragment we need to return to
                val intent=Intent(this.context,TeleHealthActivity::class.java)
                startActivity(intent)
            }
            R.id.frame_manualinput->{
                val intent=Intent(this.context,ManualInputActivity::class.java)
                startActivity(intent)
            }
            R.id.frame_medication->{
                val intent=Intent(this.context,MedicationActivity::class.java)
                startActivity(intent)
            }
            R.id.frame_measurement->{
                val intent=Intent(this.context,MeasurementActivity::class.java)
                startActivity(intent)
            }
            R.id.frame_music->{
                //todo implement music app?
                //Log.d(".....","my name is ${sharedPreferencesHelper.getUserName("user_name")}")
                //val name:String=homeDataManager.getStringItem("example","Mary Lynn")
                //Log.d("For example","my other name is $name")
            }
            R.id.frame_health_data->{
                val intent=Intent(this.context,HealthDataActivity::class.java)
                startActivity(intent)
            }
            R.id.frame_phone->{
                val intent=Intent(this.context,PhoneActivity::class.java)
                startActivity(intent)
            }
            R.id.img_date->{

            }
            R.id.img_home_profile->{
                Log.d("de", "click img_home_profile")
                val intent = Intent(context, MyProfileActivity::class.java)
                startActivity(intent)
            }
            else->{
            }
        }
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

    override fun showAvatar(path: String) {
        //TODO("Not yet implemented")
    }

    override fun showProfile(path: String) {
        //TODO("Not yet implemented")
    }

    private val dateBroadcastReceiver=object :BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            val action=intent?.action
            Log.d("date broadcast","action is :$action")
            if (//action.equals(Intent.ACTION_TIME_CHANGED)
                 //   ||action.equals(Intent.ACTION_TIMEZONE_CHANGED)
                 //   ||action.equals(Intent.ACTION_TIME_TICK)
                    action.equals(Intent.ACTION_DATE_CHANGED)){

                // TODO: 2020/10/19 update date
                val calendar=Calendar.getInstance()
                Log.d("date broadcast","time is ${calendar.get(Calendar.HOUR)}:${calendar.get(Calendar.MINUTE)}")


            }
        }
    }
}