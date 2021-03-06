package com.corbit.stationgx.pages.mainbaseactivity.welcomefragment

import dagger.Module
import dagger.Provides

@Module
class WelcomeFragmentModule {

    @Provides
    fun provideWelcomeFragmentView(welcomeFragment: WelcomeFragment):WelcomeFragmentContract.View{
        return welcomeFragment
    }

}