package com.example.stationgx.pages

import com.example.stationgx.di.DaggerStationApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class StationApplication:DaggerApplication(){


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerStationApplicationComponent.builder().application(this).build()
    }
}