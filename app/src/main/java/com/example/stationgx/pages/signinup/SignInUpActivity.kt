package com.example.stationgx.pages.signinup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.stationgx.R
import com.example.stationgx.base.BaseActivity
import com.example.stationgx.pages.mainbaseactivity.MainBaseActivity
import com.example.stationgx.pages.mainbaseactivity.homefragment.HomeFragment
import com.example.stationgx.pages.mainbaseactivity.welcomefragment.WelcomeFragment
import com.example.stationgx.pages.signinup.signinfragment.SignInFragment
import com.example.stationgx.pages.signinup.signupfragment.SignUpFragment
import kotlinx.android.synthetic.main.activity_signin_signup.*
import kotlinx.android.synthetic.main.main_base.*

class SignInUpActivity: FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin_signup)

        signinup_viewpager.adapter = SignInUpPagerAdapter(this)
    }

    open fun switchToSignUpPage() {
        signinup_viewpager.currentItem = 1
    }

    open fun switchToSignInPage() {
        signinup_viewpager.currentItem = 0
    }

    private inner class SignInUpPagerAdapter(signInUpActivity: SignInUpActivity) : FragmentStateAdapter(signInUpActivity){
        override fun getItemCount(): Int {
            return 2
        }

        override fun createFragment(position: Int): Fragment {
            when(position){
                0-> return SignInFragment()
                1-> return SignUpFragment()
            }
            return SignInFragment()
        }
    }
}