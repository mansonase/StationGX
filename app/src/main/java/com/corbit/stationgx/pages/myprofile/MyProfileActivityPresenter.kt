package com.corbit.stationgx.pages.myprofile

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.view.size
import kotlinx.android.synthetic.main.profile_emergency_contact.view.*

class MyProfileActivityPresenter(private val view: MyProfileActivityContract.IMyProfileView, private val context: Context)
    : MyProfileActivityContract.IMyProfilePresenter {

    open fun uploadProfileData() {

    }

    open fun addNewEmergencyContact(contactLL: LinearLayout) {
        val newEmergencyContact = EmergencyContact(context)
        newEmergencyContact.presenter = this
        if (contactLL.size == 0)
            newEmergencyContact.view.ib_delete_emergency_contact.visibility = View.GONE
        else
            newEmergencyContact.view.ib_delete_emergency_contact.visibility = View.VISIBLE

        contactLL.addView(newEmergencyContact)
    }

    open fun removeEmergencyContact(emergencyContact: EmergencyContact) {
        view.removeTargetEmergencyContact(emergencyContact)
    }

    open fun removeTargetEmergencyContact(emergencyContact: EmergencyContact, contactLL: LinearLayout) {
        contactLL.removeView(emergencyContact)
    }

    open fun resetMobilityBtnBg() {
        view.resetMobilityBtnBg()
    }

    open fun onMedicalEventExpBtnClick(button: Button) {
        Log.d("de", "${button.text}: "+button.tag)
        if (button.tag == null || button.tag == false) {
            button.tag = true
            view.updateMedicalEventBtn(button, true)
        }
        else {
            button.tag = false
            view.updateMedicalEventBtn(button, false)
        }
    }

    open fun onMedicalTreatmentBtnClick(button: Button) {
        if (button.tag == null || button.tag == false) {
            button.tag = true
            view.updateMedicalEventBtn(button, true)
        }
        else {
            button.tag = false
            view.updateMedicalEventBtn(button, false)
        }
    }
}