package com.example.stationgx.pages.mainbaseactivity.homefragment

import javax.inject.Inject

class HomeFragmentPresenter @Inject constructor(var view: HomeFragmentContract.View) :HomeFragmentContract.Presenter {



    override fun notificationListener(id: Int, content: String) {
        //TODO("Not yet implemented")
    }

    override fun settingUserName(userName: String) {

    }

    override fun takeView(view: HomeFragmentContract.View) {
        //TODO("Not yet implemented")
    }

    override fun dropView() {
        //TODO("Not yet implemented")
    }
}