package com.example.stationgx.data.prefs


interface SharedPreferencesHelper {

    fun getUserName(key: String):String
    fun setUserName(key:String,userName:String)

    fun setParam(key: String,obj:Any)
    fun getParam(key: String, defaultValue: Any):Any
    fun clear(key: String)
    fun clearAll()

}