package com.example.stationgx.di

import android.content.Context
import com.example.stationgx.pages.home.HomeActivityComponent
import dagger.Module
import dagger.Provides

@Module(subcomponents = [HomeActivityComponent::class])
class StationAppModule(private val context: Context) {

    @Provides
    fun provideContext():Context=context
}