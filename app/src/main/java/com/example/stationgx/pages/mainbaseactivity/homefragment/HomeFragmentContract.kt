package com.example.stationgx.pages.mainbaseactivity.homefragment

import com.example.stationgx.pages.BasePresenter
import com.example.stationgx.pages.BaseView
import java.util.*

interface HomeFragmentContract {

    interface View:BaseView{

        fun showDate(current:Date)
        fun showLastDocumenting(current:Date)
        fun showNotificationRedPoint(numbers:Int)
        fun showName(name:String)
        fun showLocation(location:String)

    }

    interface Presenter:BasePresenter<View>{

        fun notificationListner(id:Int,content:String)

    }
}