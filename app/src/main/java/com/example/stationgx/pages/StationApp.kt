package com.example.stationgx.pages

import android.app.Activity
import android.app.Application
import com.example.stationgx.di.DaggerStationAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class StationApp:Application(),HasActivityInjector{


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
        //TODO("Not yet implemented")
        return activityDispatchingAndroidInjector
    }
}