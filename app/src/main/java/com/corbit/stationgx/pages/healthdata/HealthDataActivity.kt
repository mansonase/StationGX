package com.corbit.stationgx.pages.healthdata

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.corbit.stationgx.R
import com.corbit.stationgx.base.BaseActivity
import com.corbit.stationgx.pages.mainbaseactivity.MainBaseActivity
import kotlinx.android.synthetic.main.main_healthdata.*


class HealthDataActivity: BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_healthdata)

        btn_healthdata.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_healthdata->{
                val bundle=Bundle()
                bundle.putInt(getString(R.string.fragment_indicator),1)
                val intent=Intent(this, MainBaseActivity::class.java)
                intent.putExtra(getString(R.string.bundle_between_activities),bundle)
                startActivity(intent)
            }
        }
    }
}