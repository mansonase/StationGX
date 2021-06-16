package com.corbit.stationgx.pages.signinup.signupfragment

import android.util.Log
import android.widget.EditText
import com.google.android.gms.common.util.CollectionUtils.mapOf
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class SignUpFragmentPresenter(private val view: SignUpFragmentContract.ISignUpFragmentView) {

    var pwdVisibility: Boolean = false
    var pwdConfirmVisibility: Boolean = false

    open fun startSignUp(firstName: EditText, lastName: EditText, email: EditText, pwd: EditText, pwdConfirm: EditText) {
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
                val user = Firebase.auth.currentUser

                val map: MutableMap<String, Any> = HashMap()
                map["userEmail"] = emailStr
                map["userFirstName"] = firstNameStr
                map["userLastName"] = lastNameStr
                map["name"] = "$firstNameStr $lastNameStr"
                map["userUid"] = user!!.uid

                val ref = Firebase.database.reference
                ref.child("emergencyusers").child(user!!.uid).setValue(map).addOnSuccessListener {
                    Log.d("de", "add user success")
                    view.goToMainPage()
                }.addOnFailureListener {
                    Log.d("de", "add user failed, ${it.toString()}")
                }

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
            view.updatePwdConfirmCorrect()
        }
        else {
            view.setSignUpBtnEnable(false)
            view.updatePwdConfirmVisibility(this.pwdConfirmVisibility)
        }
    }

    open fun updatePwdVisibility() {
        this.pwdVisibility = !this.pwdVisibility
        view.updatePwdVisibility(this.pwdVisibility)
        view.updatePwdInputMethod(this.pwdVisibility)
    }

    open fun updatePwdConfirmVisibility() {
        this.pwdConfirmVisibility = !this.pwdConfirmVisibility
        view.updatePwdConfirmVisibility(this.pwdConfirmVisibility)
        view.updatePwdConfirmInputMethod(this.pwdConfirmVisibility)
    }
}