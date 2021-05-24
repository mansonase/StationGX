package com.example.stationgx.pages.signinup.signupfragment

class SignUpFragmentContract {
    interface ISignUpFragmentView {
        fun goToSignInPage()

        fun clearInputField()

        fun showWrongEmailFormatAlert(show: Boolean)

        fun presentEmptyEmailAlert()

        fun showWrongPwdFormatAlert(show: Boolean)

        fun showPwdConfirmInputField(show: Boolean)

        fun setSignUpBtnEnable(enable: Boolean)
    }

    interface ISignUpFragmentPresenter {

    }
}