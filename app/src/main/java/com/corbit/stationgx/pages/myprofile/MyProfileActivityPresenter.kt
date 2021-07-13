package com.corbit.stationgx.pages.myprofile

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.view.size
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.profile_emergency_contact.view.*

class MyProfileActivityPresenter(private val view: MyProfileActivityContract.IMyProfileView, private val context: Context)
    : MyProfileActivityContract.IMyProfilePresenter {

    open fun uploadProfileData() {

    }

    open fun addNewEmergencyContact(contactLL: LinearLayout) {
        val newEmergencyContact = EmergencyContact(context)
        newEmergencyContact.presenter = this
        if (contactLL.size == 0) {
            newEmergencyContact.view.ib_delete_emergency_contact.visibility = View.GONE
            newEmergencyContact.view.tv_emergency_contact_title.visibility = View.GONE
        }
        else
            newEmergencyContact.view.ib_delete_emergency_contact.visibility = View.VISIBLE

        contactLL.addView(newEmergencyContact)
        view.refreshEmergencyContacts()
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

    open fun onAllergiesBtnClick(button: Button) {

    }

    open fun onMedicalEventExpBtnClick(button: Button) {
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

    open fun onSaveBtnClick(profile: MyProfile) {
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        val ref = FirebaseDatabase.getInstance().getReference("emergencyusers")
        ref.child(uid).child("profile").setValue(profile.toMap()).addOnSuccessListener {
            Log.d("de", "onSave success")
        }.addOnCanceledListener {
            Log.d("de", "onSave canceled")
        }.addOnFailureListener {
            Log.d("de", "onSave failed")
        }
    }

    open fun fetchProfileData() {
        Log.d("de", "fetchProfileData")
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        val ref = FirebaseDatabase.getInstance().getReference("emergencyusers")
        ref.child(uid).child("profile").get().addOnSuccessListener {
            val profile = it.getValue(MyProfile::class.java)
            Log.d("de","111, $profile")
            Log.d("de", "${profile?.firstName}")
            view.initProfile(profile ?: MyProfile())
        }.addOnCanceledListener {
            Log.d("de", "fetchProfileData, cancel")
            view.initProfile(MyProfile())
        }.addOnFailureListener {
            view.initProfile(MyProfile())
        }
    }
}