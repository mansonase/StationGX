package com.corbit.stationgx.application

import android.content.Context
import android.content.IntentFilter
import com.corbit.stationgx.data.prefs.SP
import com.corbit.stationgx.data.prefs.SharedPreferencesHelper
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


    @Provides
    @Singleton
    fun provideSP(sp: SP):SharedPreferencesHelper{
        return sp
    }

    @Provides
    @Singleton
    fun provideTimeIntent(filter: IntentFilter):IntentFilter{
        return filter
    }
}