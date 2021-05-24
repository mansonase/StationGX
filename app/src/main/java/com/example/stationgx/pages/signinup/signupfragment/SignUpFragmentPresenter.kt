package com.example.stationgx.pages.signinup.signupfragment

import android.util.Log
import android.widget.EditText
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpFragmentPresenter(private val view: SignUpFragmentContract.ISignUpFragmentView) {
    open fun startSignUp(firstName: EditText, lastName: EditText, email: EditText, pwd: EditText, pwdConfirm: EditText) {
        Log.d("de", "startSignUp")
        val firstNameStr = firstName?.text.toString()
        val lastNameStr = lastName?.text.toString()
        val emailStr = email?.text.toString()
        val pwdStr = pwd?.text.toString()
        val pwdConfirmStr = pwdConfirm?.text.toString()

        if (emailStr == null || emailStr == "") {
            view.presentEmptyEmailAlert()
        }
        else {
            val auth = Firebase.auth
            auth.createUserWithEmailAndPassword(emailStr, pwdStr).addOnSuccessListener {
                Log.d("de", "Sign up success, uid: ${it?.user?.uid}")
            }.addOnFailureListener {
                Log.d("de", "Sign up failed, ${it.toString()}")
            }
        }

    }

    open fun startGoogleSignUp() {

    }

    open fun startFacebookSignUp() {

    }

    open fun goToSignInPage() {
        view.goToSignInPage()
    }

    open fun clearInputField() {
        view.clearInputField()
    }

    open fun checkPwdValidation(pwdStr: String) {
        if (pwdStr == "") {
            view.showWrongPwdFormatAlert(false)
            view.showPwdConfirmInputField(false)
        }
        else {
            val reg = Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@\$!%*#?&]{6,}\$")
            if (reg.matches(pwdStr)) {
                view.showWrongPwdFormatAlert(false)
                view.showPwdConfirmInputField(true)
            } else {
                view.showPwdConfirmInputField(false)
                view.showWrongPwdFormatAlert(true)
            }
        }
    }

    open fun checkEmailValidation(emailStr: String) {
        if(emailStr != "" && !android.util.Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()) {
            view.showWrongEmailFormatAlert(true)
        }
        else {
            view.showWrongEmailFormatAlert(false)
        }
    }

    open fun checkPwdConfirmValidation(pwdStr: String, pwdConfirmStr: String) {
        if (pwdConfirmStr == pwdStr) {
            view.setSignUpBtnEnable(true)
        }
        else {
            view.setSignUpBtnEnable(false)
        }
    }


}