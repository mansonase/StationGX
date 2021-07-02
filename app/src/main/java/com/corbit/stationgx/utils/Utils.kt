package com.corbit.stationgx.utils

import java.text.SimpleDateFormat
import java.util.*

class Utils {


    companion object{
       fun createKeyIndex(): Long {
            return Calendar.getInstance().timeInMillis.toLong()
        }

        fun getDayOfMonthSuffix(n:Int):String{
            if (n in 11..13){
                return "th"
            }
            return when(n%10){
                1-> "st"
                2-> "nd"
                3-> "rd"
                else -> "th"
            }
        }

        fun createListDate(calendar: Calendar):String{


            val mFormat=SimpleDateFormat("MMMM", Locale.getDefault())
            val dFormat=SimpleDateFormat("dd", Locale.getDefault())
            val yFormat=SimpleDateFormat("yyyy", Locale.getDefault())

            val month=mFormat.format(calendar.time)
            val day=dFormat.format(calendar.time)+ getDayOfMonthSuffix(calendar.get(Calendar.DAY_OF_MONTH))
            val year=yFormat.format(calendar.time)

            return "$month $day, $year"
        }
    }
}