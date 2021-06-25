package com.corbit.stationgx.pages.mainbaseactivity.homefragment

import dagger.Module
import dagger.Provides

@Module
class HomeFragmentModule {



    @Provides
    fun provideHomeFragmentView(homeFragment: HomeFragment):HomeFragmentContract.View{
        return homeFragment
    }
}