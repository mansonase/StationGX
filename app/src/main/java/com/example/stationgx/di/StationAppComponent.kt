package com.example.stationgx.di

import android.content.Context
import dagger.Component

@Component(modules = [StationAppModule::class])
interface StationAppComponent {
    fun context():Context
}