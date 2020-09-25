package com.example.stationgx.di

import android.app.Application
import com.example.stationgx.pages.StationApp
import com.example.stationgx.pages.home.HomeActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    StationAppModule::class,
    ActivityBuilder::class
])
interface StationAppComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application:Application):Builder
        fun build():StationAppComponent
    }

    fun inject(application:StationApp)
}