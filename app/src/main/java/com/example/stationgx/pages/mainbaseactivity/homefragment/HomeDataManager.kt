package com.example.stationgx.pages.mainbaseactivity.homefragment

import com.example.stationgx.data.prefs.SharedPreferencesHelper

interface HomeDataManager:SharedPreferencesHelper{

    fun setGeneralName(key:String,name:String)
}