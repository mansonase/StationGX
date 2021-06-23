package com.example.stationgx.pages.signinup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
<<<<<<< Updated upstream:app/src/main/java/com/example/stationgx/pages/signinup/SignInUpActivity.kt
import com.example.stationgx.R
import com.example.stationgx.base.BaseActivity
import com.example.stationgx.pages.mainbaseactivity.MainBaseActivity
import com.example.stationgx.pages.mainbaseactivity.homefragment.HomeFragment
import com.example.stationgx.pages.mainbaseactivity.welcomefragment.WelcomeFragment
import com.example.stationgx.pages.signinup.signinfragment.SignInFragment
import com.example.stationgx.pages.signinup.signupfragment.SignUpFragment
=======
import com.corbit.stationgx.R
import com.corbit.stationgx.pages.mainbaseactivity.MainBaseActivity
import com.corbit.stationgx.pages.signinup.signinfragment.SignInFragment
import com.corbit.stationgx.pages.signinup.signupfragment.SignUpFragment
import com.google.firebase.auth.FirebaseAuth
>>>>>>> Stashed changes:app/src/main/java/com/corbit/stationgx/pages/signinup/SignInUpActivity.kt
import kotlinx.android.synthetic.main.activity_signin_signup.*
import kotlinx.android.synthetic.main.main_base.*
import kotlin.math.sign

class SignInUpActivity: FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (FirebaseAuth.getInstance().currentUser != null) {
            goToMainPage()
        }

        setContentView(R.layout.activity_signin_signup)

        signinup_viewpager.adapter = SignInUpPagerAdapter(this)
        signinup_viewpager.isUserInputEnabled = false
    }

    open fun goToSignUpPage() {
        signinup_viewpager.currentItem = 1
    }

    open fun goToSignInPage() {
        signinup_viewpager.currentItem = 0
    }

    open fun goToMainPage() {
        val intent = Intent(this, MainBaseActivity::class.java)
        startActivity(intent)
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