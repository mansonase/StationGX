package com.example.stationgx.pages.signinup.signinfragment

import android.view.View

class SignInFragmentPresenter(private val view: SignInFragmentContract.ISignInFragmentView): SignInFragmentContract.ISignInFragmentPresenter {

    fun switchToSignUpPage() {
        view.switchToSignUpPage()
    }

    fun clearInputField(view: View) {

    }
}