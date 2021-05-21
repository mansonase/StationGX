package com.corbit.stationgx.pages.mainbaseactivity.homefragment


import javax.inject.Inject

class HomeFragmentPresenter @Inject constructor() :HomeFragmentContract.Presenter {


    var view: HomeFragmentContract.View? = null

 override fun notificationListener(id: Int, content: String) {
        //TODO("Not yet implemented")
    }

    override fun settingUserName(userName: String) {

    }

    override fun takeView(view: HomeFragmentContract.View) {
        this.view=view
    }

    override fun dropView() {
        this.view=null
    }


}