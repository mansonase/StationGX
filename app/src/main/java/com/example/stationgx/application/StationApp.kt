package com.example.stationgx.application

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.realm.Realm


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
        Realm.init(this)
    }
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        return DaggerStationAppComponent.builder().create(this)
    }
}