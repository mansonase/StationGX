package com.corbit.stationgx.pages.signinup

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.ToggleButton
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.corbit.stationgx.R
import com.corbit.stationgx.pages.mainbaseactivity.MainBaseActivity
import com.corbit.stationgx.pages.signinup.signinfragment.SignInFragment
import com.corbit.stationgx.pages.signinup.signupfragment.SignUpFragment
import com.corbit.stationgx.services.PorcupineService
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signin_signup.*
import java.io.BufferedInputStream
import java.io.BufferedOutputStream

class SignInUpActivity: FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            copyResourceFile(R.raw.wake_words, "wake_words.ppn")
            copyResourceFile(R.raw.speech_to_intent, "speech_to_intent.rhn")
        }
        catch (e: Exception) {
            Log.d("de", "copyResourceFile, ${e.toString()}")
        }

        setContentView(R.layout.activity_signin_signup)

        if (hasRecordPermission()) {
            startPorcupineService()
            if (FirebaseAuth.getInstance().currentUser != null) {
                goToMainPage()
            }
        } else {
            requestRecordPermission()
        }

        signinup_viewpager.adapter = SignInUpPagerAdapter(this)
        signinup_viewpager.isUserInputEnabled = false
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        val recordButton = findViewById<ToggleButton>(R.id.startButton)
//        if (grantResults.size == 0 || grantResults[0] == PackageManager.PERMISSION_DENIED) {
//            recordButton.toggle()
//        } else {
//            startService()
//        }

        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startPorcupineService()
        }
    }

    private fun startPorcupineService() {
        val service = Intent(this, PorcupineService::class.java)
        startService(service)
    }

    private fun hasRecordPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestRecordPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 0)
    }

    private fun copyResourceFile(resourceId: Int, filename: String) {
        Log.d("de", "in copyResourceFile")
        val resource = resources
        val inputStream = BufferedInputStream(resource.openRawResource(resourceId), 256)
        val outputStream = BufferedOutputStream(openFileOutput(filename, MODE_PRIVATE), 256)
        var r: Int
        while (inputStream.read().also { r = it } != -1) {
            outputStream.write(r)
        }
        outputStream.flush()
        Log.d("de", "end copyResourceFile")
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

    override fun onDestroy() {
        super.onDestroy()
        Log.d("de", "SignInUpActivity.onDestroy")
        stopPorcupineService()
    }

    private fun stopPorcupineService() {
        val service = Intent(this, PorcupineService::class.java)
        stopService(service)
    }
}