package com.example.stationgx.di

import android.content.Context
import android.content.SharedPreferences
import com.example.stationgx.pages.StationApplication
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class StationApplicationModules {


    @Singleton
    @Binds
    abstract fun context(application: StationApplication):Context




/*
    @ContributesAndroidInjector
    abstract fun contributeStationApplication():StationApplication


 */

}