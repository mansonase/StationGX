package com.example.stationgx.pages.mainbaseactivity

import com.example.stationgx.base.BasePresenter

interface MainBaseActivityContract {

    interface View{
        fun onFragmentLoaded()
    }

    interface Presenter: BasePresenter<View> {
        fun loadFragment(id:Int)
    }
}