package com.example.stationgx.pages.signinup.signupfragment

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.example.stationgx.R
import com.example.stationgx.pages.signinup.SignInUpActivity
import kotlinx.android.synthetic.main.fragment_signin.*
import kotlinx.android.synthetic.main.fragment_signup.*
import kotlinx.android.synthetic.main.fragment_signup.btn_signin
import kotlinx.android.synthetic.main.fragment_signup.btn_signup
import kotlinx.android.synthetic.main.fragment_signup.et_email
import kotlinx.android.synthetic.main.fragment_signup.et_pwd

class SignUpFragment: Fragment(), SignUpFragmentContract.ISignUpFragmentView{

    lateinit var presenter: SignUpFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = SignUpFragmentPresenter(this)

        btn_signin.setOnClickListener {
            presenter.clearInputField()
            presenter.goToSignInPage()
        }

        btn_google_signup.setOnClickListener {
            presenter.startGoogleSignUp()
        }

        btn_facebook_signup.setOnClickListener {
            presenter.startFacebookSignUp()
        }

        btn_signup.setOnClickListener {
            presenter.startSignUp(et_first_name, et_last_name, et_email, et_pwd, et_pwd_confirm)
        }

        et_email.doOnTextChanged { text, start, before, count ->
            presenter.checkEmailValidation(text.toString())
        }

//        et_pwd.doOnTextChanged { text, start, before, count ->
//            presenter.checkPwdValidation(text.toString())
//        }

        et_pwd.doAfterTextChanged {
            presenter.checkPwdValidation(it.toString())
        }

//        et_pwd_confirm.doOnTextChanged { text, start, before, count ->
//            presenter.checkPwdConfirmValidation(et_pwd?.text.toString(), text.toString())
//        }

        et_pwd_confirm.doAfterTextChanged {
            presenter.checkPwdConfirmValidation(et_pwd?.text.toString(), it.toString())
        }

        ib_pwd_visibility.setOnClickListener {
            presenter.updatePwdVisibility()
        }

        ib_pwd_confirm_visibility.setOnClickListener {
            presenter.updatePwdConfirmVisibility()
        }
    }

    override fun updatePwdInputMethod(visibility: Boolean) {
        if (visibility) {
            et_pwd.transformationMethod = null
        }
        else {
            et_pwd.transformationMethod = PasswordTransformationMethod()
        }
    }

    override fun goToMainPage() {
        (activity as SignInUpActivity).goToMainPage()
    }

    override fun goToSignInPage() {
        (activity as SignInUpActivity).goToSignInPage()
    }

    override fun clearInputField() {
        et_first_name?.setText("")
        et_last_name?.setText("")
        et_email?.setText("")
        et_pwd?.setText("")
    }

    override fun showWrongEmailFormatAlert(show: Boolean) {
        et_email.error = if (show) getString(R.string.signup_wrong_email_format_wording) else null
    }

    override fun presentEmptyEmailAlert() {
        et_email.error = getString(R.string.signup_empty_email_hint_wording)
    }

    override fun showWrongPwdFormatAlert(show: Boolean) {
        tv_wrong_pwd_format_wording?.visibility = if(show) View.VISIBLE else View.GONE
    }

    override fun showPwdConfirmInputField(show: Boolean) {
        et_pwd_confirm?.visibility = if(show) View.VISIBLE else View.GONE
        ib_pwd_confirm_visibility?.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun setSignUpBtnEnable(enable: Boolean) {
        btn_signup?.isEnabled = enable
    }

    override fun updatePwdVisibility(visibility: Boolean) {
        if (visibility) {
            ib_pwd_visibility.setImageResource(R.drawable.ic_visibility)
        }
        else {
            ib_pwd_visibility.setImageResource(R.drawable.ic_visibility_off)
        }
    }

    override fun updatePwdConfirmVisibility(visibility: Boolean) {
        if (visibility) {
            ib_pwd_confirm_visibility.setImageResource(R.drawable.ic_visibility)
        }
        else {
            ib_pwd_confirm_visibility.setImageResource(R.drawable.ic_visibility_off)
        }
    }

    override fun updatePwdConfirmInputMethod(visibility: Boolean) {
        if (visibility) {
            et_pwd_confirm.transformationMethod = null
        }
        else {
            et_pwd_confirm.transformationMethod = PasswordTransformationMethod()
        }
    }

    override fun updatePwdConfirmCorrect() {
        ib_pwd_confirm_visibility.setImageResource(R.drawable.ic_check)
    }
}