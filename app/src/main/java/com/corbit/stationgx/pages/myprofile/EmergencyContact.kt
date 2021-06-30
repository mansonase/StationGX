package com.corbit.stationgx.pages.myprofile

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.corbit.stationgx.R
import kotlinx.android.synthetic.main.profile_emergency_contact.view.*

class EmergencyContact @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0)
    : ConstraintLayout(context, attributeSet, defStyleAttr) {
    val view = View.inflate(context, R.layout.profile_emergency_contact, this)

    open fun getEmergencyContactInfo() {
    }
}