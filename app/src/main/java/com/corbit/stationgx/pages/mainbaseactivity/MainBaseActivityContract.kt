package com.corbit.stationgx.pages.mainbaseactivity

import com.corbit.stationgx.base.BasePresenter

interface MainBaseActivityContract {

    interface View{
        fun onFragmentLoaded()
    }

    interface Presenter: BasePresenter<View> {
        fun loadFragment(id:Int)
    }
}