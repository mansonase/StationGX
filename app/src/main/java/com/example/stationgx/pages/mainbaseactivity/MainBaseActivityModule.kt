package com.example.stationgx.pages.mainbaseactivity

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides

@Module
class MainBaseActivityModule {

    @Provides
    fun provideMainBaseActivityView(mainbaseactivity:MainBaseActivity):MainBaseActivityContract.View{
        return mainbaseactivity
    }

    @Provides
    fun provideMainBaseActivityPresenter(view:MainBaseActivityContract.View):MainBaseActivityContract.Presenter{
        return MainBaseActivityPresenter(view)
    }
}