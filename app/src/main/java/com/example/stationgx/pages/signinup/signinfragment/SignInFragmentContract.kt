package com.example.stationgx.pages.signinup.signinfragment

class SignInFragmentContract {
    interface ISignInFragmentView {
        fun switchToSignUpPage()

        fun clearInputField()

        fun showWrongPwdAlert()

        fun hideWrongPwdAlert()

        fun presentWrongEmailFormatAlert()

        fun presentEmptyEmailAlert()

        fun presentResetPwdAlert()
    }

    interface ISignInFragmentPresenter {

    }
}