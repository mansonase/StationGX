package com.example.stationgx.pages.mainbaseactivity.homefragment

import com.example.stationgx.data.prefs.SharedPreferencesHelper
import javax.inject.Inject

class DM @Inject constructor(private val sharedPreferencesHelper: SharedPreferencesHelper):HomeDataManager{


    override fun setGeneralName(key: String, name: String) {
        //todo???
    }

    override fun getUserName(key: String): String {
       return sharedPreferencesHelper.getUserName(key)
    }

    override fun setUserName(key: String, userName: String) {
        sharedPreferencesHelper.setUserName(key,userName)
    }

    /*
    override fun <T>setParam(key: String, obj: T) {
        sharedPreferencesHelper.setParam(key,obj)
    }
     */
    override fun putStringItem(key: String, value: String) {
        sharedPreferencesHelper.putStringItem(key,value)
    }

    override fun putIntItem(key: String, value: Int) {
        sharedPreferencesHelper.putIntItem(key,value)
    }

    override fun putBooleanItem(key: String, value: Boolean) {
        sharedPreferencesHelper.putBooleanItem(key,value)
    }

    /*
    override fun <T>getParam(key: String, defaultValue: T): T {
        return sharedPreferencesHelper.getParam(key,defaultValue)
    }

     */

    override fun getStringItem(key: String, defaultValue: String): String {
        return sharedPreferencesHelper.getStringItem(key,defaultValue)
    }

    override fun getIntItem(key: String, defaultValue: Int): Int {
        return sharedPreferencesHelper.getIntItem(key,defaultValue)
    }

    override fun getBooleanItem(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferencesHelper.getBooleanItem(key,defaultValue)
    }

    override fun clear(key: String) {
        sharedPreferencesHelper.clear(key)
    }

    override fun clearAll() {
        sharedPreferencesHelper.clearAll()
    }
}