package com.example.stationgx.pages.mainbaseactivity.homefragment

import com.example.stationgx.data.prefs.SP
import com.example.stationgx.data.prefs.SharedPreferencesHelper
import dagger.Module
import dagger.Provides

@Module
class HomeFragmentModule {

    @Provides
    fun provideHomeDataManager(dm: DM):HomeDataManager{
        return dm
    }

    @Provides
    fun provideSharedPreferencesHelper(sp: SP):SharedPreferencesHelper{
        return sp
    }

    @Provides
    fun provideHomeFragmentView(homeFragment: HomeFragment):HomeFragmentContract.View{
        return homeFragment
    }
}