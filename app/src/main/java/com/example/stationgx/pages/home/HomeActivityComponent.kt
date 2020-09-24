package com.example.stationgx.pages.home

import com.example.stationgx.di.StationAppComponent
import dagger.Component
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(
        modules = [HomeActivityModule::class]
)
interface HomeActivityComponent :AndroidInjector<HomeActivity>{

    @Subcomponent.Factory
    interface Factory:AndroidInjector.Factory<HomeActivity>{}

    /*
    @Subcomponent.Builder
    interface Builder{
        fun build():HomeActivityComponent
    }

     */
}