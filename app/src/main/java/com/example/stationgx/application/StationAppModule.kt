package com.example.stationgx.application

import android.content.Context
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