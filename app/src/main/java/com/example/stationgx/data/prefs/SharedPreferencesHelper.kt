package com.example.stationgx.data.prefs


interface SharedPreferencesHelper {

    fun getUserName(key: String):String
    fun setUserName(key:String,userName:String)

    //fun <T>setParam(key: String,obj:T)
    fun putStringItem(key: String,value:String)
    fun putIntItem(key: String,value:Int)
    fun putBooleanItem(key: String,value:Boolean)
    //fun <T>getParam(key: String, defaultValue:T):T
    fun getStringItem(key: String,defaultValue:String):String
    fun getIntItem(key: String,defaultValue:Int):Int
    fun getBooleanItem(key: String,defaultValue:Boolean):Boolean
    fun clear(key: String)
    fun clearAll()

}