package com.corbit.stationgx.pages.manuelinput.bloodpressure.presenter

import android.text.SpannableStringBuilder
import com.haibin.calendarview.Calendar


interface FragmentContract {
    interface IView{

        fun updateDocuments(string:SpannableStringBuilder)
        fun updateCalendar(map: HashMap<String,Calendar>)
    }
    interface IPresenter{

        fun getDayData(startTime: Int)
        fun getWeekData(startTime: Int)
        fun getMonthData(startTime: Int)
    }
}