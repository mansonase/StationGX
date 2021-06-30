package com.corbit.stationgx.pages.myprofile

import android.content.Context

class MyProfileActivityPresenter(private val view: MyProfileActivityContract.IMyProfileView, private val context: Context)
    : MyProfileActivityContract.IMyProfilePresenter {

        open fun uploadProfileData() {
            
        }
}