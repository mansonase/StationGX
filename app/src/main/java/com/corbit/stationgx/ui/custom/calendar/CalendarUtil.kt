package com.corbit.stationgx.ui.custom.calendar

import com.haibin.calendarview.Calendar

class CalendarUtil {

    companion object{
        fun getWeekFromCalendar(calendar: Calendar): Int {
            val date = java.util.Calendar.getInstance()
            date.set(calendar.year, calendar.month - 1, calendar.day)
            // sunday==0, monday==1, tuesday==2, wednesday==3, thursday==4, friday==5, saturday==6
            return date.get(java.util.Calendar.DAY_OF_WEEK) - 1
        }
    }
}