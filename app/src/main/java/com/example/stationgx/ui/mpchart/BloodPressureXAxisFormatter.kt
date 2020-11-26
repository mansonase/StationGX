package com.example.stationgx.ui.mpchart

import android.util.Log
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter

class BloodPressureXAxisFormatter(private val interval:Array<String>):ValueFormatter() {

    //var interval:Array<String>? = interval

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {

        Log.d("testing...","value is $value")
        val index=value.toInt()/getDivideNumber(interval)
        //val index=value.toInt()
        return interval[index]
    }

    private fun getDivideNumber(interval: Array<String>):Int{

        var devidedNumber=1

        when(interval.size){
            5->{
                devidedNumber=6
            }
            8->{
                devidedNumber=24
            }
            6->{
                devidedNumber=7
            }
        }
        return devidedNumber
    }
}