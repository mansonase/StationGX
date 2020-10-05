package com.example.stationgx.pages.mainbaseactivity.homefragment

import javax.inject.Inject

class HomeFragmentPresenter @Inject constructor(var view: HomeFragmentContract.View) :HomeFragmentContract.Presenter {


    override fun notificationListner(id: Int, content: String) {
        //TODO("Not yet implemented")
    }

    override fun takeView(view: HomeFragmentContract.View) {
        //TODO("Not yet implemented")
    }

    override fun dropView() {
        //TODO("Not yet implemented")
    }
}