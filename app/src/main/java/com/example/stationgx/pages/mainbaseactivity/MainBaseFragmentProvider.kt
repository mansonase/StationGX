package com.example.stationgx.pages.mainbaseactivity

import com.example.stationgx.pages.mainbaseactivity.homefragment.HomeFragment
import com.example.stationgx.pages.mainbaseactivity.homefragment.HomeFragmentModule
import com.example.stationgx.pages.mainbaseactivity.welcomefragment.WelcomeFragment
import com.example.stationgx.pages.mainbaseactivity.welcomefragment.WelcomeFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainBaseFragmentProvider {

    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun provideHomeFragmentFactory(): HomeFragment

    @ContributesAndroidInjector(modules = [WelcomeFragmentModule::class])
    abstract fun provideWelcomeFragmentFactory():WelcomeFragment
}