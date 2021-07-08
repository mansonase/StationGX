package com.corbit.stationgx.data.db.manualinput.bloodpressure

interface FragmentPersistence {

    fun getDayData(startTime: Int)
    fun getWeekData(startTime: Int)
    fun getMonthData(startTime: Int)

}