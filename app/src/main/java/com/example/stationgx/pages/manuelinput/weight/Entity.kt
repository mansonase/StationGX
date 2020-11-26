package com.example.stationgx.pages.manuelinput.weight

class Entity(private var date: String, private var time: String, private var weight: Float, private var change:Float, private var target:Float, private var bmi: Float, private var status:String) {

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
    fun setWeight(weight: Float){
        this.weight=weight
    }
    fun getWeight():Float{
        return weight
    }
    fun setChange(change: Float){
        this.change=change
    }
    fun getChange():Float{
        return change
    }
    fun setTarget(target: Float){
        this.target=target
    }
    fun getTarget():Float{
        return target
    }
    fun setBMI(bmi: Float){
        this.bmi=bmi
    }
    fun getBMI():Float{
        return bmi
    }
    fun setStatus(status: String){
        this.status=status
    }
    fun getStatus():String{
        return status
    }
}