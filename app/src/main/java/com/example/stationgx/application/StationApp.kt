package com.example.stationgx.application

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class StationApp:DaggerApplication(){

    /*
    @Inject
    lateinit var activityDispatchingAndroidInjector:DispatchingAndroidInjector<Activity>
    override fun onCreate() {
        super.onCreate()
        DaggerStationAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }
    override fun activityInjector(): AndroidInjector<Activity> {

        return activityDispatchingAndroidInjector
    }

 */
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        return DaggerStationAppComponent.builder().create(this)
    }
}