package com.example.stationgx.application

import android.content.Context
import com.example.stationgx.data.prefs.SP
import com.example.stationgx.data.prefs.SharedPreferencesHelper
import dagger.Module
import dagger.Provides
import io.realm.Realm
import javax.inject.Singleton

@Module
class StationAppModule {

    @Provides
    @Singleton
    fun provideContext(application: StationApp):Context{
        return application
    }


    @Provides
    @Singleton
    fun provideSP(sp: SP):SharedPreferencesHelper{
        return sp
    }
}