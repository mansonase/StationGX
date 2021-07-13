package com.corbit.stationgx.pages.myprofile

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.core.view.size
import androidx.core.widget.addTextChangedListener
import com.corbit.stationgx.R
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.fragment_signup.*
import kotlinx.android.synthetic.main.profile_emergency_contact.view.*
import kotlin.math.pow

class MyProfileActivity:AppCompatActivity(), MyProfileActivityContract.IMyProfileView {

    lateinit var presenter: MyProfileActivityPresenter
    lateinit var mobilityArr: Array<Button>
    lateinit var profile: MyProfile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        profile = MyProfile()
        initMobilityArr()
        setupGenderSpinner()
        setupBloodTypeSpinner()
        setupRhesusSpinner()

        presenter = MyProfileActivityPresenter(this, this)
        presenter.fetchProfileData()

        setupDataInputField()

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
            Log.d("de", "on click save, profile: ${profile.toMap()}")
            presenter.onSaveBtnClick(profile)
        }
    }

    private fun setupDataInputField() {
        et_profile_first_name?.addTextChangedListener{
            Log.d("de", "et_first_name.addTextChange, ${it.toString()}")
            profile.firstName = it.toString()
        }

        et_profile_last_name?.addTextChangedListener{
            profile.lastName = it.toString()
        }

        et_profile_email?.addTextChangedListener {
            profile.email = it.toString()
        }

        et_profile_phone?.addTextChangedListener{
            profile.phone = it.toString()
        }

        et_height?.addTextChangedListener{
            profile.height = if (it.toString().isEmpty()) -1 else it.toString().toInt()
            calculateBMI()
        }

        et_weight?.addTextChangedListener {
            profile.weight = if (it.toString().isEmpty()) -1 else it.toString().toInt()
            calculateBMI()
        }

        sp_gender?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, selection: Int, id: Long) {
                profile.gender = selection
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                profile.gender = 0
            }
        }

        sp_blood_type?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, selection: Int, id: Long) {
                profile.bloodType = selection
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                profile.bloodType = 0
            }
        }

        sp_rhesus?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, selection: Int, id: Long) {
                profile.rhesus = selection
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                profile.rhesus = 0
            }
        }

        et_food_allergies?.addTextChangedListener{
            profile.foodAllergies = it.toString()
        }

        et_drug_intolerance?.addTextChangedListener {
            profile.drugIntolerance = it.toString()
        }
    }

    override fun initProfile(profile: MyProfile) {
        this.profile = profile
        this.setupProfile()
    }

    private fun setupGenderSpinner() {
        val genderAdapter: ArrayAdapter<String> = object : ArrayAdapter<String> (
                this,
                R.layout.profile_spinner_textview,
                resources.getStringArray(R.array.gender_selection)
        ) {}
        sp_gender.adapter = genderAdapter
    }

    private fun setupBloodTypeSpinner() {
        val bloodTypeAdapter: ArrayAdapter<String> = object : ArrayAdapter<String> (
                this,
                R.layout.profile_spinner_textview,
                resources.getStringArray(R.array.blood_type_array)
                ) {}
        sp_blood_type.adapter = bloodTypeAdapter
    }

    private fun setupRhesusSpinner() {
        val rhesusAdapter: ArrayAdapter<String> = object : ArrayAdapter<String> (
                this,
                R.layout.profile_spinner_textview,
                resources.getStringArray(R.array.rhesus_array)
        ) {}
        sp_rhesus.adapter = rhesusAdapter
    }

    private fun setupProfile() {
        et_profile_first_name.setText(this.profile.firstName)
        et_profile_last_name.setText(this.profile.lastName)
        et_profile_email.setText(this.profile.email)
        et_profile_phone.setText(this.profile.phone)
        et_height.setText(if (this.profile.height > 0) this.profile.height.toString() else "")
        et_weight.setText(if (this.profile.weight > 0) this.profile.weight.toString() else "")
        this.calculateBMI()
        et_food_allergies.setText(this.profile.foodAllergies)
        et_drug_intolerance.setText(this.profile.drugIntolerance)
        sp_gender.setSelection(this.profile.gender)
        sp_blood_type.setSelection(this.profile.bloodType)
        sp_rhesus.setSelection(this.profile.rhesus)
    }

    private fun calculateBMI() {
        if (this.profile.height > 0 && this.profile.weight > 0) {
            tv_bmi_value.text = String.format("%.1f", (this.profile.weight / (this.profile.height / 100.0).pow(2.0)))
            tv_bmi_value.visibility = View.VISIBLE
        }
        else {
            tv_bmi_value.visibility = View.INVISIBLE
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

    override fun refreshEmergencyContacts() {
        for (i in 0 until ll_emergency_contact.size) {
            ll_emergency_contact[i].tv_emergency_contact_title.text = "Emergency Contact ${i+1}"
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun removeTargetEmergencyContact(emergencyContact: EmergencyContact) {
        presenter.removeTargetEmergencyContact(emergencyContact, ll_emergency_contact)
    }
}