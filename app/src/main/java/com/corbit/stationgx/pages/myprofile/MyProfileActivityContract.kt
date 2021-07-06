package com.corbit.stationgx.pages.myprofile

import android.widget.Button

class MyProfileActivityContract {
    interface IMyProfileView {
        fun removeTargetEmergencyContact(emergencyContact: EmergencyContact)

        fun resetMobilityBtnBg()

        fun updateMedicalEventBtn(button: Button, selected: Boolean)

        fun updateMedicalTreatmentBtn(button: Button, selected: Boolean)

        fun refreshEmergencyContacts()

        fun initProfile(profile: MyProfile)
    }

    interface IMyProfilePresenter {

    }
}