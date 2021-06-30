package com.corbit.stationgx.pages.myprofile

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.corbit.stationgx.R
import kotlinx.android.synthetic.main.activity_profile.*

class MyProfileActivity:AppCompatActivity(), MyProfileActivityContract.IMyProfileView {

    lateinit var presenter: MyProfileActivityPresenter
    lateinit var mobilityArr: Array<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        initMobilityArr()

        presenter = MyProfileActivityPresenter(this, this)
        presenter.addNewEmergencyContact(ll_emergency_contact)
        ib_add_contact.setOnClickListener {
            presenter.addNewEmergencyContact(ll_emergency_contact)
        }

        btn_mobility_1.setOnClickListener{
            resetMobilityBtnBg()
        }

        btn_mobility_2.setOnClickListener {
            resetMobilityBtnBg()
        }

        btn_mobility_3.setOnClickListener {
            resetMobilityBtnBg()
        }

        btn_mobility_4.setOnClickListener {
            resetMobilityBtnBg()
        }

        btn_mobility_5.setOnClickListener {
            resetMobilityBtnBg()
        }

        btn_save.setOnClickListener{

        }
    }

    private fun initMobilityArr() {
        mobilityArr[0] = btn_mobility_1
        mobilityArr[1] = btn_mobility_2
        mobilityArr[2] = btn_mobility_3
        mobilityArr[3] = btn_mobility_4
        mobilityArr[4] = btn_mobility_5
    }

    private fun resetMobilityBtnBg() {
        for (view in mobilityArr) {
            view.setBackgroundResource(R.drawable.bg_profile_btn_unselected)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun removeTargetEmergencyContact(emergencyContact: EmergencyContact) {
        presenter.removeTargetEmergencyContact(emergencyContact, ll_emergency_contact)
    }
}