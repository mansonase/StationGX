package com.example.stationgx.di

import com.example.stationgx.pages.mainbaseactivity.homefragment.HomeActivity
import com.example.stationgx.pages.mainbaseactivity.homefragment.HomeActivityModule
import com.example.stationgx.pages.mainbaseactivity.MainBaseActivity
import com.example.stationgx.pages.mainbaseactivity.MainBaseActivityModule
import com.example.stationgx.pages.mainbaseactivity.MainBaseFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {


    @ContributesAndroidInjector(modules = [MainBaseActivityModule::class,MainBaseFragmentProvider::class])
    abstract fun binMainBaseActivity():MainBaseActivity

}