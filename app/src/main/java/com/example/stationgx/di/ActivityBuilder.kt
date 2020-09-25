package com.example.stationgx.di

import com.example.stationgx.pages.home.HomeActivity
import com.example.stationgx.pages.home.HomeActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun bindHomeActivity(): HomeActivity

}