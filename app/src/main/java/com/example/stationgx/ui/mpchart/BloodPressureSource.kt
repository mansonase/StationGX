package com.example.stationgx.ui.mpchart

import android.util.Log
import com.github.mikephil.charting.data.BarEntry
import java.sql.Timestamp
import java.util.*
import kotlin.collections.ArrayList

class BloodPressureSource(private val duration:String) {

    private val maxDia=110
    private val minDia=50

    private fun getCount(duration: String):Int{
        var count=0
        when(duration){
            "today"->{
                count=3
            }
            "week"->{
                count=21
            }
            "month"->{
                count=120
            }
        }
        return count
    }

    fun getBarEntry():List<BarEntry>{

        val list=ArrayList<BarEntry>()

        val count=getCount(duration)

        val calendar=Calendar.getInstance()

        val timestamps= arrayOfNulls<Timestamp>(count)

        when(duration){
            "today"->{
                for (i in 0 until count){
                    calendar.set(Calendar.HOUR_OF_DAY,(Math.random()*24).toInt())
                    calendar.set(Calendar.MINUTE,(Math.random()*60).toInt())
                    timestamps[i]= Timestamp(calendar.timeInMillis)
                }
            }
            "week"->{
                for (i in 0 until count){
                    calendar.set(Calendar.DAY_OF_WEEK,(i/3))
                    calendar.set(Calendar.HOUR_OF_DAY,(Math.random()*24).toInt())
                    timestamps[i]= Timestamp(calendar.timeInMillis)
                }
            }
            "month"->{
                for (i in 0 until count){
                    calendar.set(Calendar.DAY_OF_MONTH,(i/4))
                    timestamps[i]= Timestamp(calendar.timeInMillis)
                }
            }
        }

        for (i in 0 until count){


            var dia_bp=0
            while (dia_bp<minDia){
                dia_bp=(Math.random()*maxDia).toInt()
            }
            val rangeBP= floatArrayOf(dia_bp.toFloat(),40f)

            var index:Int?
            if (i==0){
                index=1
            }else{

                index=(Math.random()*168).toInt()
            }
            Log.d("testinging","index is $index")
            val barEntry=BarEntry((index).toFloat(),rangeBP)


            list.add(barEntry)
        }
        return list
    }
}