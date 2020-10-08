package com.example.stationgx.application

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    StationAppModule::class,
    ActivityBuilder::class
])
interface StationAppComponent:AndroidInjector<StationApp> {

    /*
    @Component.Builder
       function build() and application() is defined in AndroidInjector.Builder
         therefore we can extend Builder which already has these two function inside.
    interface Builder{
        @BindsInstance
        fun application(application:Application):Builder
        fun build():StationAppComponent
    }
     */
    @Component.Builder
    abstract class Builder: AndroidInjector.Builder<StationApp>() {}



    /* this function is included in AndroidInjector<StationApp>, so no needs to declare this function
    fun inject(application:StationApp)

     */
}