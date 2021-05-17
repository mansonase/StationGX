package com.example.stationgx.application

import android.content.Context
import android.os.Environment
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.realm.Realm
import io.realm.RealmConfiguration
import java.io.File


class StationApp:DaggerApplication(){

    /*
    @Inject
    lateinit var activityDispatchingAndroidInjector:DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {

        return activityDispatchingAndroidInjector
    }


 */
    override fun onCreate() {
        super.onCreate()
        //Realm.init(this)
        setupRealm()
    }
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        return DaggerStationAppComponent.builder().create(this)
    }


    fun setupRealm(){
        Realm.init(this)
        val mRealmConfiguration=RealmConfiguration.Builder()
                .name("StationDB")
                .schemaVersion(0)
                .directory(File(getDiskCacheDir(this)+"/station_db"))
                .build()

        Realm.setDefaultConfiguration(mRealmConfiguration)

        Realm.init(this)
    }

    fun getDiskCacheDir(context:Context):String{

        var cachePath:String

        if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()
                ||!Environment.isExternalStorageRemovable()){
            cachePath=context.externalCacheDir!!.path
        }else{
            cachePath=context.cacheDir.path
        }
        return cachePath
    }
}