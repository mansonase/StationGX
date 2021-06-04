package com.corbit.stationgx.ui.custom.calendar.bp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.corbit.stationgx.R
import com.corbit.stationgx.pages.manuelinput.bloodpressure.view.BPCalendarFragment

class FragmentSwitcher:Fragment(),View.OnClickListener{

    lateinit var textToday:TextView
    lateinit var textWeek:TextView
    lateinit var textMonth:TextView
    var duration="today"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments==null){
            duration="today"
            Log.d("bpswitcher","arguments is null")
        }else{
            duration= arguments!!.getString("range").toString()
            Log.d("bpswitcher","arguments is not null, $duration")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.fragment_calendar_switcher,container,false)

/*
        if (arguments==null){
            duration="today"
            Log.d("bpswitcher","arguments is null")
        }else{
            duration= arguments!!.getString("range").toString()
            Log.d("bpswitcher","arguments is not null, $duration")
        }

 */



        textToday=view.findViewById(R.id.switcher_today)
        textWeek=view.findViewById(R.id.switcher_week)
        textMonth=view.findViewById(R.id.switcher_month)
        textToday.setOnClickListener(this)
        textWeek.setOnClickListener(this)
        textMonth.setOnClickListener(this)
        view.findViewById<ImageView>(R.id.switcher_cancel).setOnClickListener(this)
        Log.d("fragmentSwitcher","onCreateView")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("fragmentSwitcher","onviewcreated")
        //setTextBackground(duration)
    }



    fun setTextBackground(duration:String){
        this.duration=duration
        when(duration){
            "today"->{
                textToday.setBackgroundResource(R.drawable.calendar_today_pressed)
                textWeek.setBackgroundResource(R.drawable.calendar_week)
                textMonth.setBackgroundResource(R.drawable.calendar_month)
            }
            "week"->{
                textToday.setBackgroundResource(R.drawable.calendar_today)
                textWeek.setBackgroundResource(R.drawable.calendar_week_pressed)
                textMonth.setBackgroundResource(R.drawable.calendar_month)
            }
            "month"->{
                textToday.setBackgroundResource(R.drawable.calendar_today)
                textWeek.setBackgroundResource(R.drawable.calendar_week)
                textMonth.setBackgroundResource(R.drawable.calendar_month_pressed)
            }
        }
    }

    fun fragmentSetDuration(howLong:String){
        this.duration=howLong
        Log.d("switcherrr",howLong)
        setTextBackground(duration)
    }


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.switcher_today->{
                (parentFragment as BPCalendarFragment).controlCalendar("today","")
            }
            R.id.switcher_week->{
                (parentFragment as BPCalendarFragment).controlCalendar("week","")
                Log.d("fs","week????")
            }
            R.id.switcher_month->{
                (parentFragment as BPCalendarFragment).controlCalendar("month","")
                Log.d("fs","month????")
            }
            R.id.switcher_cancel->{
                (parentFragment as BPCalendarFragment).controlCalendar("switcher",duration)
                Log.d("fs","cancel????")
            }
        }
    }
}