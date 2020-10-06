package com.example.stationgx.pages.mainbaseactivity.welcomefragment

import com.example.stationgx.pages.BasePresenter
import com.example.stationgx.pages.BaseView

interface WelcomeFragmentContract {
    interface View:BaseView{
        fun showFlowerAnimation()
        fun showDate()
        fun showMoney()
        fun showWater()
        fun showFertile()
        fun showWeather()
    }
    interface Presenter:BasePresenter<View>{
        fun takeFlowerPicture()
    }
}