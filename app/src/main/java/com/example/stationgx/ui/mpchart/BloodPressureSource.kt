package com.example.stationgx.ui.mpchart

import com.github.mikephil.charting.data.BarEntry

class BloodPressureSource(private val duration:String) {

    private val maxDia=110
    private val minDia=60

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

        for (i in 0..count){


            var dia_bp=0
            while (dia_bp<minDia){
                dia_bp=(Math.random()*maxDia).toInt()
            }
            val rangeBP= floatArrayOf(dia_bp.toFloat(),40f)
            val barEntry=BarEntry((i).toFloat(),rangeBP)


            list.add(barEntry)
        }
        return list
    }
}