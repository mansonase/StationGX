package com.example.stationgx.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class StationAppModule(private val context: Context) {

    @Provides
    fun provideContext():Context=context
}