package com.corbit.stationgx.pages.myprofile

class MyProfile {
    var firstName: String = ""

    var lastName: String = ""

    var email: String = ""

    var phone: String = ""

    var city: String = ""

    var country: String = ""

    var gender: Int = 0

    var birthday: Long = 0

    var height: Int = -1

    var weight: Int = -1

    var bloodType: Int = 0

    var allergies: Boolean = false

    var foodAllergies: String = ""

    var drugIntolerance: String = ""

    var mobility: Int = 0

    var emergencyContacts: Array<EmergencyContact> = arrayOf()

    open fun toMap(): String{
        var map: MutableMap<String, Any> = mutableMapOf()
        map["firstName"] = firstName
        map["lastName"] = lastName
        map["email"] = email
        map["phone"] = phone
        map["city"] = city
        map["country"] = country
        map["gender"] = gender
        map["birthday"] = birthday
        map["height"] = height
        map["weight"] = weight
        map["bloodType"] = bloodType
        map["allergies"] = allergies
        map["foodAllergies"] = foodAllergies
        map["drugIntolerance"] = drugIntolerance
        map["mobility"] = mobility
        map["emergencyContacts"] = emergencyContacts

        return map.toString()
    }
}