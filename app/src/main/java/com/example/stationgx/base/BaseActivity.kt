package com.example.stationgx.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import dagger.android.support.DaggerAppCompatActivity

open class BaseActivity:DaggerAppCompatActivity(), BaseView {

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

    override fun showMessage(id: Int) {
        showMessage(getText(id))
    }

    override fun showMessage(text: CharSequence) {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        //TODO("Not yet implemented")
    }

    override fun showLoading(text: CharSequence) {
        //TODO("Not yet implemented")
    }

    override fun cancelLoading() {
        //TODO("Not yet implemented")
    }
}