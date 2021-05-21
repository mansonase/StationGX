package com.example.stationgx.pages.signinup.signupfragment

import android.widget.EditText

class SignUpFragmentPresenter(private val view: SignUpFragmentContract.ISignUpFragmentView) {
    open fun startSignUp(firstName: EditText, lastName: EditText, email: EditText, pwd: EditText) {

    }

    open fun startGoogleSignUp() {

    }

    open fun startFacebookSignUp() {

    }

    open fun goToSignInPage() {

    }
}