package com.example.stationgx.ui.custom.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.stationgx.R
import kotlinx.android.synthetic.main.main_manualinput.*

class FragmentToday:Fragment(),View.OnClickListener{
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.fragment_calendar_today,container,false)
        view.findViewById<ImageView>(R.id.calendar_today_left).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.calendar_today_right).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.calendar_today_return).setOnClickListener(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.calendar_today_left->{

            }
            R.id.calendar_today_right->{

            }
            R.id.calendar_today_return->{

            }
        }
    }
}