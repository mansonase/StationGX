package com.example.stationgx.di

import com.example.stationgx.pages.StationApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules =[
    AndroidSupportInjectionModule::class,
    StationApplicationModules::class
])
public interface StationApplicationComponent : AndroidInjector<StationApplication>{

    @Component.Builder
    //abstract class Builder:AndroidInjector.Builder<StationApplication>(){}
    interface Builder{
        @BindsInstance
        fun application(application: StationApplication):Builder
        fun build():StationApplicationComponent
    }
}