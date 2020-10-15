package com.example.stationgx.pages.mainbaseactivity.homefragment

import com.example.stationgx.base.BasePresenter
import com.example.stationgx.base.BaseView
import java.util.*

interface HomeFragmentContract {

    interface View: BaseView {

        fun showDate(current:Date)
        fun showLastDocumenting(current:Date)
        fun showNotificationRedPoint(numbers:Int)
        fun showName(name:String)
        fun showLocation(location:String)

    }

    interface Presenter: BasePresenter<View> {

        fun notificationListener(id:Int, content:String)

        fun settingUserName(userName:String)
    }
}