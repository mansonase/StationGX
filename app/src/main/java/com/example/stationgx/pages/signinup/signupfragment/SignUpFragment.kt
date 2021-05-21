package com.example.stationgx.pages.signinup.signupfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.stationgx.R
import kotlinx.android.synthetic.main.fragment_signup.*

class SignUpFragment: Fragment(), SignUpFragmentContract.ISignUpFragmentView{

    lateinit var presenter: SignUpFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = SignUpFragmentPresenter(this)

        btn_signin.setOnClickListener {

        }

        btn_google_signup.setOnClickListener {

        }

        btn_facebook_signup.setOnClickListener {

        }

    }
}