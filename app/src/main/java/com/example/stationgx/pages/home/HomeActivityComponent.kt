package com.example.stationgx.pages.home

import com.example.stationgx.di.StationAppComponent
import dagger.Component

@Component(
        dependencies = [StationAppComponent::class],modules = [HomeActivityModule::class]
)
interface HomeActivityComponent {

}