package com.example.stationgx.data.prefs

import android.content.Context
import android.content.SharedPreferences
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


    override fun setParam(key: String, obj: Any) {
        when(obj){
            obj is String-> mPrefs.edit().putString( key,obj as String).apply()
            obj is Int->    mPrefs.edit().putInt(    key,obj as Int).apply()
            obj is Boolean->mPrefs.edit().putBoolean(key,obj as Boolean).apply()
            obj is Float->  mPrefs.edit().putFloat(  key,obj as Float).apply()
            obj is Long->   mPrefs.edit().putLong(   key,obj as Long).apply()
        }
    }

    override fun getParam(key: String, defaultValue: Any): Any {
        when(defaultValue){
            defaultValue is String  -> return mPrefs.getString( key,defaultValue as String) as String
            defaultValue is Int     -> return mPrefs.getInt(    key,defaultValue as Int)
            defaultValue is Boolean -> return mPrefs.getBoolean(key,defaultValue as Boolean)
            defaultValue is Float   -> return mPrefs.getFloat(  key,defaultValue as Float)
            defaultValue is Long    -> return mPrefs.getLong(   key,defaultValue as Long)
        }
        return Any()
    }

    override fun clear(key: String) {
        mPrefs.edit().remove(key).apply()
    }

    override fun clearAll() {
        mPrefs.edit().clear().apply()
    }
}