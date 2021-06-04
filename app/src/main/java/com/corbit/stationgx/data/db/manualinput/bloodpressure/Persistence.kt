package com.corbit.stationgx.data.db.manualinput.bloodpressure

interface Persistence {


    fun sentToRealm(bpBean: BloodPressureBean)
    fun queryRealm(query:String)
    fun updatePerson(bpBean: BloodPressureBean)
}