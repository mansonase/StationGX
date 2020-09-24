package com.example.stationgx.di

import com.example.stationgx.pages.home.HomeActivity
import com.example.stationgx.pages.home.HomeActivityComponent
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [HomeActivityComponent::class])
abstract class ActivityBindModule {

    @Binds
    @IntoMap
    @ClassKey(HomeActivity::class)
    abstract fun bindHomeActivityInjectorFactory(
            factory: HomeActivityComponent.Factory):AndroidInjector.Factory<*>
    
}