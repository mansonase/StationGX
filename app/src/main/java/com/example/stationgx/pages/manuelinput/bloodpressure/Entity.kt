package com.example.stationgx.pages.manuelinput.bloodpressure

class Entity(private var date: String, private var time: String,  private var bloodPressure: String, private var note: String, private var abnormal:Boolean) {

    fun setDate(date: String){
        this.date=date
    }
    fun getDate():String{
        return date
    }
    fun setTime(time:String){
        this.time=time
    }
    fun getTime():String{
        return time
    }
    fun setBloodPressure(bloodPressure: String){
        this.bloodPressure=bloodPressure
    }
    fun getBloodPressure():String{
        return bloodPressure
    }
    fun setNote(note: String){
        this.note=note
    }
    fun getNote():String{
        return note
    }
    fun setAbnormal(abnormal: Boolean){
        this.abnormal=abnormal
    }
    fun getAbnormal():Boolean{
        return abnormal
    }
}