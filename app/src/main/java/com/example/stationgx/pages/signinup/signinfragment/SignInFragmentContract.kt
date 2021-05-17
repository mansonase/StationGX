package com.example.stationgx.pages.signinup.signinfragment

import android.view.View

class SignInFragmentContract {
    interface ISignInFragmentView {
        fun switchToSignUpPage()

        fun clearInputField(view: View)
    }

    interface ISignInFragmentPresenter {

    }
}