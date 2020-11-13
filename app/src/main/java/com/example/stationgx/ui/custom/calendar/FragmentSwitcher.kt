package com.example.stationgx.ui.custom.calendar

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.stationgx.R
import com.example.stationgx.pages.manuelinput.bloodpressure.BPCalendarFragment

class FragmentSwitcher:Fragment(),View.OnClickListener{

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.fragment_calendar_switcher,container,false)
        view.findViewById<TextView>(R.id.switcher_today).setOnClickListener(this)
        view.findViewById<TextView>(R.id.switcher_week).setOnClickListener(this)
        view.findViewById<TextView>(R.id.switcher_month).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.switcher_cancel).setOnClickListener(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.switcher_today->{
                (parentFragment as BPCalendarFragment).controlCalendar()
            }
            R.id.switcher_week->{
                Log.d("fs","week????")
            }
            R.id.switcher_month->{
                Log.d("fs","month????")
            }
            R.id.switcher_cancel->{
                Log.d("fs","cancel????")
            }
        }
    }
}