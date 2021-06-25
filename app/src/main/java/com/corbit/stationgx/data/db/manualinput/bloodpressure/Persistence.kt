package com.corbit.stationgx.data.db.manualinput.bloodpressure

interface Persistence<T> {


    fun sentToRealm(data:T)
    fun queryRealm(query:String)
    fun updatePerson(data: T)

    fun getDayData(startTime: Int)
    fun getWeekData(startTime: Int)
    fun getMonthData(startTime: Int)

}