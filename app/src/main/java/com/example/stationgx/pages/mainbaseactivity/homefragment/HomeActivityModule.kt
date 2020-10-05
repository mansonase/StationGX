package com.example.stationgx.pages.mainbaseactivity.homefragment

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides

@Module
class HomeActivityModule {

    @Provides
    fun provideSharedPreferences(context: Context):SharedPreferences{
        return context.getSharedPreferences("test",Context.MODE_PRIVATE)
    }
}