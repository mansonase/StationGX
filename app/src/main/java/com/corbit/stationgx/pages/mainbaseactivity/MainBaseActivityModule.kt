package com.corbit.stationgx.pages.mainbaseactivity

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