package com.corbit.stationgx.pages.manuelinput.bloodpressure.presenter

import com.haibin.calendarview.Calendar
import io.realm.RealmResults


interface FragmentContract {
    interface IView{

        fun updateDocuments(string: String)
        fun updateCalendar(map: HashMap<String,Calendar>)
    }
    interface IPresenter{

        fun getDayData(startTime: Int)
        fun getWeekData(startTime: Int)
        fun getMonthData(startTime: Int)
    }
}