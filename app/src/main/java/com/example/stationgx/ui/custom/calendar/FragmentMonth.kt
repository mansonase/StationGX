package com.example.stationgx.ui.custom.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.stationgx.R

class FragmentMonth:Fragment(),View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.fragment_calendar_month,container,false)
        view.findViewById<ImageView>(R.id.calendar_month_left).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.calendar_month_right).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.calendar_month_return).setOnClickListener(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.calendar_month_left->{

            }
            R.id.calendar_month_right->{

            }
            R.id.calendar_month_return->{

            }
        }
    }
}