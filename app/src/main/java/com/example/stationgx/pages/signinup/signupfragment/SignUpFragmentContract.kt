package com.example.stationgx.pages.signinup.signupfragment

class SignUpFragmentContract {
    interface ISignUpFragmentView {
        fun goToMainPage()

        fun goToSignInPage()

        fun clearInputField()

        fun showWrongEmailFormatAlert(show: Boolean)

        fun presentEmptyEmailAlert()

        fun showWrongPwdFormatAlert(show: Boolean)

        fun showPwdConfirmInputField(show: Boolean)

        fun setSignUpBtnEnable(enable: Boolean)

        fun updatePwdVisibility(visibility: Boolean)

        fun updatePwdInputMethod(visibility: Boolean)

        fun updatePwdConfirmVisibility(visibility: Boolean)

        fun updatePwdConfirmInputMethod(visibility: Boolean)

        fun updatePwdConfirmCorrect()
    }

    interface ISignUpFragmentPresenter {

    }
}