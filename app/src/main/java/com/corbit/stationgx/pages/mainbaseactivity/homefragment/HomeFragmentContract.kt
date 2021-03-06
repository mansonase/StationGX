package com.corbit.stationgx.pages.mainbaseactivity.homefragment

import com.corbit.stationgx.base.BasePresenter
import com.corbit.stationgx.base.BaseView
import java.util.*

interface HomeFragmentContract {

    interface View: BaseView {

        fun showLastDocumenting(current:Date)
        fun showNotificationRedPoint(numbers:Int)
        fun showName(name:String)
        fun showLocation(location:String)
        fun showAvatar(path:String)
        fun showProfile(path: String)


    }

    interface Presenter: BasePresenter<View> {

        fun notificationListener(id:Int, content:String)

        fun settingUserName(userName:String)
    }
}