package com.example.stationgx.pages.manuelinput

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.stationgx.R
import com.example.stationgx.base.BaseActivity
import com.example.stationgx.pages.mainbaseactivity.MainBaseActivity
import kotlinx.android.synthetic.main.main_manualinput.*

class ManualInputActivity: BaseActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_manualinput)

        btn_manualinput.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_manualinput-> {
                val intent = Intent(this, MainBaseActivity::class.java)
                val bundle=Bundle()
                bundle.putInt(getString(R.string.fragment_indicator),1)
                intent.putExtra(getString(R.string.bundle_between_activities),bundle)
                startActivity(intent)
            }
        }
    }
}