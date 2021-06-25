package com.corbit.stationgx.pages.mainbaseactivity.welcomefragment

import com.corbit.stationgx.base.BasePresenter
import com.corbit.stationgx.base.BaseView

interface WelcomeFragmentContract {
    interface View: BaseView {
        fun showFlowerAnimation()
        fun showDate()
        fun showMoney()
        fun showWater()
        fun showFertile()
        fun showWeather()
    }
    interface Presenter: BasePresenter<View> {
        fun takeFlowerPicture()
    }
}