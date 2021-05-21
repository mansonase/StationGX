package com.example.stationgx.pages.signinup.signinfragment

import android.text.SpannableString

class SignInFragmentContract {
    interface ISignInFragmentView {
        fun goToMainPage()

        fun goToSignUpPage()

        fun clearInputField()

        fun showWrongPwdAlert()

        fun hideWrongPwdAlert()

        fun presentWrongEmailFormatAlert()

        fun presentEmptyEmailAlert()

        fun presentResetPwdAlert(title: String, message: SpannableString)
    }

    interface ISignInFragmentPresenter {

    }
}