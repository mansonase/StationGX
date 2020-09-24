package com.example.stationgx.pages.manuelinput

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.stationgx.R
import com.example.stationgx.pages.BaseActivity
import com.example.stationgx.pages.home.HomeActivity
import kotlinx.android.synthetic.main.main_manualinput.*

class ManualInputActivity:BaseActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_manualinput)

        btn_manualinput.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_manualinput-> {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }
}