package com.example.stationgx.pages.signinup.signinfragment

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.stationgx.R
import com.example.stationgx.pages.signinup.SignInUpActivity
import kotlinx.android.synthetic.main.fragment_signin.*

class SignInFragment: Fragment(), SignInFragmentContract.ISignInFragmentPresenter, SignInFragmentContract.ISignInFragmentView {

    lateinit var presenter: SignInFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_signin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = SignInFragmentPresenter(this)
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
    }

    override fun switchToSignUpPage() {
        (activity as SignInUpActivity).switchToSignUpPage()
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
        TODO("Not yet implemented")
    }

    override fun presentEmptyEmailAlert() {
        TODO("Not yet implemented")
    }

    override fun presentResetPwdAlert() {
        TODO("Not yet implemented")
    }
}