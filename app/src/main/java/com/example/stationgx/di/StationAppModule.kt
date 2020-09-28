package com.example.stationgx.di

import android.app.Application
import android.content.Context
import com.example.stationgx.pages.StationApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StationAppModule {

    @Provides
    @Singleton
    fun provideContext(application: StationApp):Context{
        return application
    }
}