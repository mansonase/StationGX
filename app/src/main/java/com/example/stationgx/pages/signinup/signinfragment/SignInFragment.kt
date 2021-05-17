package com.example.stationgx.pages.signinup.signinfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.stationgx.R
import com.example.stationgx.pages.signinup.SignInUpActivity

class SignInFragment: Fragment(), SignInFragmentContract.ISignInFragmentPresenter, SignInFragmentContract.ISignInFragmentView {

    lateinit var presenter: SignInFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_signin, container, false)
        presenter = SignInFragmentPresenter(this)
        view.findViewById<Button>(R.id.btn_signup).setOnClickListener(View.OnClickListener {
            presenter.switchToSignUpPage()
        })
        return view
    }

    override fun switchToSignUpPage() {
        (activity as SignInUpActivity).switchToSignUpPage()
    }

    override fun clearInputField(view: View) {
        view.findViewById<EditText>(R.id.et_email).setText("")
        view.findViewById<EditText>(R.id.et_pwd).setText("")
    }
}