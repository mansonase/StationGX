package com.example.stationgx.ui.mpchart.weight

import android.util.Log
import com.github.mikephil.charting.data.Entry
import java.util.*
import kotlin.collections.ArrayList

class WeightSource(private val duration:String) {

    private val maxWeight=65.0f
    private val minWeight=63.0f

    private var maxIndex=0

    private fun getCount(duration: String):Int{
        var count=0
        when(duration){
            "today"->{
                count=3
                maxIndex=24
            }
            "week"->{
                count=21
                maxIndex=168
            }
            "month"->{
                count=120
                maxIndex=30
            }
        }
        return count
    }

    fun getLineEntry():List<Entry>{

        val list=ArrayList<Entry>()

        val count=getCount(duration)

        //todo 這一part沒有用?!
        /*
        val calendar_bp=Calendar.getInstance()
        val timestamps= arrayOfNulls<Timestamp>(count)
        when(duration){
            "today"->{
                for (i in 0 until count){
                    calendar_bp.set(Calendar.HOUR_OF_DAY,(Math.random()*24).toInt())
                    calendar_bp.set(Calendar.MINUTE,(Math.random()*60).toInt())

                    timestamps[i]= Timestamp(calendar_bp.timeInMillis)
                }
            }
            "week"->{
                for (i in 0 until count){
                    calendar_bp.set(Calendar.DAY_OF_WEEK,(i/3))
                    calendar_bp.set(Calendar.HOUR_OF_DAY,(Math.random()*24).toInt())
                    timestamps[i]= Timestamp(calendar_bp.timeInMillis)
                }
            }
            "month"->{
                for (i in 0 until count){
                    calendar_bp.set(Calendar.DAY_OF_MONTH,(i/4))
                    timestamps[i]= Timestamp(calendar_bp.timeInMillis)
                }
            }
        }

         */

        for (i in 1 .. count){//3 or 21 or 120

            val weight=(Math.random()*(maxWeight-minWeight)+minWeight).toFloat()

            var index=(i*maxIndex/count).toFloat()

            Log.d("testinging","index is $index")
            val entry=Entry((index),weight)


            list.add(entry)
        }


        return list
    }
}