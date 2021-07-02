package com.corbit.stationgx.ui.mpchart

import android.util.Log
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class XAxisFormatter:IndexAxisValueFormatter() {


    var day=0
    var count=0

    override fun getFormattedValue(value: Float): String {

        val calendar=Calendar.getInstance()
        calendar.timeInMillis=(value.toLong()*1000)

        val formatTime=SimpleDateFormat("HH", Locale.getDefault())

        var realTime=formatTime.format(calendar.time)+":00"


        if (count==0){
            day=calendar.get(Calendar.DAY_OF_MONTH)
        }
        count++

        if (count>0&&(day!=calendar.get(Calendar.DAY_OF_MONTH))){
            realTime="24:00"
        }

        Log.d("findingHour",realTime)

        return realTime
    }

}