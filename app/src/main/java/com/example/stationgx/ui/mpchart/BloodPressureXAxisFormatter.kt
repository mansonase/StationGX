package com.example.stationgx.ui.mpchart

import android.util.Log
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter

class BloodPressureXAxisFormatter(days:Array<String>):ValueFormatter() {

    var days:Array<String>? = days

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {

        Log.d("testing...","value is $value")
        val index=value.toInt()/24
        return days!![index]
    }
}