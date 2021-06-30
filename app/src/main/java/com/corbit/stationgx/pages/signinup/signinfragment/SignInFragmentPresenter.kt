package com.corbit.stationgx.pages.signinup.signinfragment

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.EditText
import androidx.core.content.ContextCompat
import com.corbit.stationgx.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInFragmentPresenter(private val view: SignInFragmentContract.ISignInFragmentView, private val context: Context): SignInFragmentContract.ISignInFragmentPresenter {

    fun switchToSignUpPage() {
        view.goToSignUpPage()
    }

    fun clearInputField() {
        this.view.clearInputField()
    }

    fun startLoginWithEmail(email: EditText, pwd: EditText) {
        var emailStr = email.text.toString()
        var pwdStr = pwd.text.toString()

//        if ("abc@abc.com" == emailStr && "123qwe" == pwdStr) {
//            view.hideWrongPwdAlert()
//        }
//        else {
//            view.showWrongPwdAlert()
//        }

        val auth = Firebase.auth
        auth.signInWithEmailAndPassword(emailStr, pwdStr).addOnSuccessListener {
            Log.d("de", "success:　${it.user?.uid}")
            view.hideWrongPwdAlert()
            view.clearInputField()
            view.goToMainPage()

        }.addOnFailureListener {
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
        if (email == null || email.text.toString() == "") {
            view.presentEmptyEmailAlert()
        }
        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches())
            view.presentWrongEmailFormatAlert()
        else {
            val auth = Firebase.auth
            auth.sendPasswordResetEmail(email.text.toString()).addOnSuccessListener {
                val title = context.getString(R.string.signin_resend_pwd_title)
                val message1 = context.getString(R.string.signin_resend_pwd_message_1)
                val emailStr = email.text.toString()
                val message2 = context.getString(R.string.signin_resend_pwd_message_2)
                var spannableString: SpannableString = SpannableString(message1 + "\n" + emailStr + "\n" + message2)
                spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.word_blue)), message1.length, (message1 + "\n" + emailStr).length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

                view.presentResetPwdAlert(title, spannableString)
            }
        }
    }
}