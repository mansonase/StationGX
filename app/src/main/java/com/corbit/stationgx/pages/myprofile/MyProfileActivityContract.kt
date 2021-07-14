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

        fun refreshBrainImg(selected: Boolean)

        fun refreshHeartImg(selected: Boolean)

        fun refreshKidneyImg(selected: Boolean)

        fun refreshEndocrineImg(selected: Boolean)

        fun refreshSkinImg(selected: Boolean)

        fun refreshLungImg(selected: Boolean)

        fun refreshLiverImg(selected: Boolean)

        fun refreshSkeletalImg(selected: Boolean)

        fun presentSavedResult(result: String)
    }

    interface IMyProfilePresenter {

    }
}