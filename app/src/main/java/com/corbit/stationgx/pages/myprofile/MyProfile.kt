package com.corbit.stationgx.pages.myprofile

class MyProfile {
    lateinit var firstName: String

    lateinit var lastName: String

    lateinit var email: String

    lateinit var phone: String

    lateinit var city: String

    lateinit var country: String

    var gender: Int = 0

    var birthday: Long = 0

    var height: Int = -1

    var weight: Int = -1

    var bloodType: Int = 0

    var allergies: Boolean = false

    lateinit var foodAllergies: String

    lateinit var drugIntolerance: String

    var mobility: Int = 0

    lateinit var emergencyContacts: Array<EmergencyContact>

    open fun toMap() {

    }
}