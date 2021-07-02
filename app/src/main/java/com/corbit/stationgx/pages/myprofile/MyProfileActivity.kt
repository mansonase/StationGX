package com.corbit.stationgx.pages.myprofile

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
            presenter.resetMobilityBtnBg()
            btn_mobility_1.setBackgroundResource(R.drawable.bg_blue_c0e3f4_round_start_15)
        }

        btn_mobility_2.setOnClickListener {
            presenter.resetMobilityBtnBg()
            btn_mobility_2.setBackgroundResource(R.drawable.bg_blue_c0e3f4_stroke_e5f6ff)
        }

        btn_mobility_3.setOnClickListener {
            presenter.resetMobilityBtnBg()
            btn_mobility_3.setBackgroundResource(R.drawable.bg_blue_c0e3f4_stroke_e5f6ff)
        }

        btn_mobility_4.setOnClickListener {
            presenter.resetMobilityBtnBg()
            btn_mobility_4.setBackgroundResource(R.drawable.bg_blue_c0e3f4_stroke_e5f6ff)
        }

        btn_mobility_5.setOnClickListener {
            presenter.resetMobilityBtnBg()
            btn_mobility_5.setBackgroundResource(R.drawable.bg_blue_c0e3f4_round_end_15)
        }

        btn_heart_attack.setOnClickListener {
            presenter.onMedicalEventExpBtnClick(btn_heart_attack)
        }

        btn_stroke.setOnClickListener {
            presenter.onMedicalEventExpBtnClick(btn_stroke)
        }

        btn_bleeding.setOnClickListener {
            presenter.onMedicalEventExpBtnClick(btn_bleeding)
        }

        btn_cancer.setOnClickListener {
            presenter.onMedicalEventExpBtnClick(btn_cancer)
        }

        btn_faint_or_fall.setOnClickListener {
            presenter.onMedicalEventExpBtnClick(btn_faint_or_fall)
        }

        btn_dialysis.setOnClickListener {
            presenter.onMedicalEventExpBtnClick(btn_dialysis)
        }

        btn_surgery.setOnClickListener {
            presenter.onMedicalEventExpBtnClick(btn_surgery)
        }

        btn_others.setOnClickListener {
            presenter.onMedicalEventExpBtnClick(btn_others)
        }

        btn_graphic_nerves.setOnClickListener {
            presenter.onMedicalTreatmentBtnClick(btn_graphic_nerves)
        }



        btn_save.setOnClickListener{

        }
    }

    private fun initMobilityArr() {
        mobilityArr = arrayOf(btn_mobility_1, btn_mobility_2, btn_mobility_3, btn_mobility_4, btn_mobility_5)
    }

    override fun resetMobilityBtnBg() {
        mobilityArr[0].setBackgroundResource(R.drawable.bg_transparent_stroke_c0e3f4_round_start_15)
        mobilityArr[1].setBackgroundResource(R.drawable.bg_transparent_stroke_c0e3f4)
        mobilityArr[2].setBackgroundResource(R.drawable.bg_transparent_stroke_c0e3f4)
        mobilityArr[3].setBackgroundResource(R.drawable.bg_transparent_stroke_c0e3f4)
        mobilityArr[4].setBackgroundResource(R.drawable.bg_transparent_stroke_c0e3f4_round_end_15)
    }

    override fun updateMedicalEventBtn(button: Button, selected: Boolean) {
        if (selected) {
            button.setTextColor(Color.WHITE)
            button.setBackgroundResource(R.drawable.bg_profile_btn_selected)
        }
        else {
            button.setTextColor(ContextCompat.getColor(this, R.color.blue_0288d1))
            button.setBackgroundResource(R.drawable.bg_profile_btn_unselected)
        }
    }

    override fun updateMedicalTreatmentBtn(button: Button, selected: Boolean) {
        if (selected) {
            button.setTextColor(Color.WHITE)
            button.setBackgroundResource(R.drawable.bg_profile_btn_selected)
        }
        else {
            button.setTextColor(ContextCompat.getColor(this, R.color.blue_0288d1))
            button.setBackgroundResource(R.drawable.bg_profile_btn_unselected)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun removeTargetEmergencyContact(emergencyContact: EmergencyContact) {
        presenter.removeTargetEmergencyContact(emergencyContact, ll_emergency_contact)
    }
}