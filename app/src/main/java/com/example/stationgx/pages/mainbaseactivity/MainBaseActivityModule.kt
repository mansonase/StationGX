package com.example.stationgx.pages.mainbaseactivity

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides

@Module
class MainBaseActivityModule {

    @Provides
    fun provideSharedPreferences(context: Context):SharedPreferences{
        return context.getSharedPreferences("MainBase",Context.MODE_PRIVATE)
    }
}