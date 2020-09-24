package com.example.stationgx.pages.home

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class HomeActivityModule {
    @Provides
    fun provideSP(context: Context)=context.getSharedPreferences("Cooker",Context.MODE_PRIVATE)

}