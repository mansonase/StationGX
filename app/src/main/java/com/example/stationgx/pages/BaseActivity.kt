package com.example.stationgx.pages

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import dagger.android.support.DaggerAppCompatActivity

open class BaseActivity:DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideNavigationBar()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        reHideNavigationBar(hasFocus)
    }




    private fun hideNavigationBar(){
        window.decorView.systemUiVisibility=View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }
    private fun reHideNavigationBar(isFocus:Boolean){
        if (isFocus){
            window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            .or(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
                            .or(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                            .or(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
                            .or(View.SYSTEM_UI_FLAG_FULLSCREEN)
                            .or(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
    }
}