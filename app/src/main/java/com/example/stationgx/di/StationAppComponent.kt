package com.example.stationgx.di

import android.content.Context
import com.example.stationgx.pages.home.HomeActivityComponent
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [
    StationAppModule::class,
    AndroidInjectionModule::class,
    ActivityBindModule::class
])
@Singleton
interface StationAppComponent {
    //fun context():Context
    fun homeActivityComponent():HomeActivityComponent.Factory
}