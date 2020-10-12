package com.example.stationgx.data.prefs

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SP @Inject constructor(context: Context) :SharedPreferencesHelper {

    private var mPrefs: SharedPreferences = context.getSharedPreferences("station_record",Context.MODE_PRIVATE)


    override fun getUserName(key:String): String {
        return mPrefs.getString(key,"Jane Dao").toString()
    }

    override fun setUserName(key: String,userName: String) {
        mPrefs.edit().putString(key,userName).apply()
    }


    /*
    override fun <T>setParam(key: String, obj: T) {
        when(obj){
            obj is String-> mPrefs.edit().putString( key,obj as String).apply()
            obj is Int->    mPrefs.edit().putInt(    key,obj as Int).apply()
            obj is Boolean->mPrefs.edit().putBoolean(key,obj as Boolean).apply()
            obj is Float->  mPrefs.edit().putFloat(  key,obj as Float).apply()
            obj is Long->   mPrefs.edit().putLong(   key,obj as Long).apply()
        }
    }
     */

    override fun putStringItem(key: String, value: String) {
        mPrefs.edit().putString(key,value).apply()
    }

    override fun putIntItem(key: String, value: Int) {
        mPrefs.edit().putInt(key,value).apply()
    }

    override fun putBooleanItem(key: String, value: Boolean) {
        mPrefs.edit().putBoolean(key,value).apply()
    }

    /*
        override fun <T>getParam(key: String, defaultValue: T): T {
            Log.d("test","what......$key, default is $defaultValue")

            val value = when(defaultValue){
                defaultValue is String  -> mPrefs.getString( key, defaultValue as String)!!
                defaultValue is Int     -> mPrefs.getInt(    key,defaultValue as Int)
                defaultValue is Boolean -> mPrefs.getBoolean(key,defaultValue as Boolean)
                defaultValue is Float   -> mPrefs.getFloat(  key,defaultValue as Float)
                defaultValue is Long    -> mPrefs.getLong(   key,defaultValue as Long)
                else-> "Nothing"
            }
            return value as T
        }
         */
    override fun getStringItem(key: String, defaultValue: String): String {
        return mPrefs.getString(key,defaultValue)!!
    }

    override fun getIntItem(key: String, defaultValue: Int): Int {
        return mPrefs.getInt(key,defaultValue)
    }

    override fun getBooleanItem(key: String, defaultValue: Boolean): Boolean {
        return mPrefs.getBoolean(key,defaultValue)
    }

    override fun clear(key: String) {
        mPrefs.edit().remove(key).apply()
    }

    override fun clearAll() {
        mPrefs.edit().clear().apply()
    }
}