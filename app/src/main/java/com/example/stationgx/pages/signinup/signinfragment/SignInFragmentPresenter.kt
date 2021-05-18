package com.example.stationgx.pages.signinup.signinfragment

import android.util.Log
import android.widget.EditText

class SignInFragmentPresenter(private val view: SignInFragmentContract.ISignInFragmentView): SignInFragmentContract.ISignInFragmentPresenter {

    fun switchToSignUpPage() {
        view.switchToSignUpPage()
    }

    fun clearInputField() {
        this.view.clearInputField()
    }

    fun startLoginWithEmail(email: EditText, pwd: EditText) {
        Log.d("de", "startLoginWithEmail")
        Log.d("de", "email: "+email.text.toString())
        Log.d("de", "pwd: "+pwd.text.toString())
        var emailStr = email.text.toString()
        var pwdStr = pwd.text.toString()

        if ("abc@abc.com" == emailStr && "123qwe" == pwdStr) {
            view.hideWrongPwdAlert()
        }
        else {
            view.showWrongPwdAlert()
        }
    }

    fun startGoogleLogin() {
        Log.d("de", "startGoogleLogin")
    }

    fun startFacebookLogin() {
        Log.d("de", "startFacebookLogin")
    }

    fun forgotPwd(email: EditText) {

    }
}