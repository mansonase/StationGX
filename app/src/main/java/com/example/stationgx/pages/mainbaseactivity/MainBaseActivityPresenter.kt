package com.example.stationgx.pages.mainbaseactivity

import android.util.Log
import javax.inject.Inject

class MainBaseActivityPresenter @Inject constructor(val mainView: MainBaseActivityContract.View) :MainBaseActivityContract.Presenter {
//contractor 不要帶入view的參數, 請用takeview來帶. 之後需改成其他的ex sharepreferences

    val TAG=this::class.java.simpleName

    override fun loadFragment(id: Int) {
        Log.d(TAG,"load fragment No.$id")
    }

    override fun takeView(view: MainBaseActivityContract.View) {
        //TODO("Not yet implemented")
    }

    override fun dropView() {
        //TODO("Not yet implemented")
    }
}