package com.corbit.stationgx.pages.signinup.signinfragment

import android.os.Bundle
import android.text.SpannableString
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.corbit.stationgx.R
import com.corbit.stationgx.pages.signinup.SignInUpActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_signin.*

class SignInFragment: Fragment(), SignInFragmentContract.ISignInFragmentPresenter, SignInFragmentContract.ISignInFragmentView {

    lateinit var presenter: SignInFragmentPresenter
    lateinit var auth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        auth = Firebase.auth
        return inflater.inflate(R.layout.fragment_signin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = SignInFragmentPresenter(this, requireContext())
        btn_signup?.setOnClickListener {
            presenter.clearInputField()
            presenter.switchToSignUpPage()
        }
        btn_google_signin?.setOnClickListener {
            presenter.startGoogleLogin()
        }
        btn_fb_signin?.setOnClickListener {
            presenter.startFacebookLogin()
        }
        btn_signin?.setOnClickListener {
            presenter.startLoginWithEmail(et_email, et_pwd)
        }
        btn_forgot_pwd?.setOnClickListener {
            presenter.forgotPwd(et_email)
        }
    }

    override fun goToMainPage() {
        (activity as SignInUpActivity).goToMainPage()
    }

    override fun goToSignUpPage() {
        (activity as SignInUpActivity).goToSignUpPage()
    }

    override fun clearInputField() {
        et_email.setText("")
        et_pwd.setText("")
    }

    override fun showWrongPwdAlert() {
        tv_wrong_email_pwd_wording.visibility = View.VISIBLE
    }

    override fun hideWrongPwdAlert() {
        tv_wrong_email_pwd_wording.visibility = View.GONE
    }

    override fun presentWrongEmailFormatAlert() {
        et_email.error = getString(R.string.signin_wrong_email_format_wording)
    }

    override fun presentEmptyEmailAlert() {
        et_email.error = getString(R.string.signin_empty_email_hint_wording)
    }

    override fun presentResetPwdAlert(title:String, message: SpannableString) {
        val dialog = SignInCustomAlertDialog(requireContext())
        dialog.show()
        dialog.setCustomTitle(title)
        dialog.setCustomMessage(message)
        dialog.getNegativeBtn().setOnClickListener {
            dialog.dismiss()
        }
        dialog.getPositiveBtn().setOnClickListener {
            dialog.dismiss()
        }
    }
}