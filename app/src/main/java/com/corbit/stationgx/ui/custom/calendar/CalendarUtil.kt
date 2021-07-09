package com.corbit.stationgx.ui.custom.calendar

import com.haibin.calendarview.Calendar

class CalendarUtil {

    companion object{

        const val day="day"
        const val week="week"
        const val month="month"

        var duration=""
        var map=HashMap<String,Calendar>()

        fun getWeekFromCalendar(calendar: Calendar): Int {
            val date = java.util.Calendar.getInstance()
            date.set(calendar.year, calendar.month - 1, calendar.day)

            var whatDay=date.get(java.util.Calendar.DAY_OF_WEEK) - 1

            if (whatDay==0){
                whatDay=7
            }
            //return的值: monday==1, tuesday==2, wednesday==3, thursday==4, friday==5, saturday==6, sunday==0
            return whatDay
        }


        fun getDays(calendar: java.util.Calendar,range:String):Int{
            val days: Int = when(range){
                day->{
                    1
                }
                week->{
                    7
                }
                else->{
                    calendar.getActualMaximum(java.util.Calendar.DAY_OF_MONTH)
                }
            }
            return days
        }
    }
}