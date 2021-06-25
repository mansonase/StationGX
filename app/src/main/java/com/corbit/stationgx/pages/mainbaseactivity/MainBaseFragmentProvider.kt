package com.corbit.stationgx.pages.mainbaseactivity

import com.corbit.stationgx.pages.mainbaseactivity.homefragment.HomeFragment
import com.corbit.stationgx.pages.mainbaseactivity.homefragment.HomeFragmentModule
import com.corbit.stationgx.pages.mainbaseactivity.welcomefragment.WelcomeFragment
import com.corbit.stationgx.pages.mainbaseactivity.welcomefragment.WelcomeFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainBaseFragmentProvider {

    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun provideHomeFragmentFactory(): HomeFragment

    @ContributesAndroidInjector(modules = [WelcomeFragmentModule::class])
    abstract fun provideWelcomeFragmentFactory():WelcomeFragment
}