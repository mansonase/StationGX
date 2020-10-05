package com.example.stationgx.pages.mainbaseactivity

import com.example.stationgx.pages.BasePresenter
import com.example.stationgx.pages.BaseView

interface MainBaseActivityContract {

    interface View{
        fun onFragmentLoaded()
    }

    interface Presenter:BasePresenter<View>{
        fun loadFragment(id:Int)
    }
}