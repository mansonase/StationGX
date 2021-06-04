package com.corbit.stationgx.data.db.manualinput.bloodpressure

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class BloodPressureBean : RealmObject() {

    private val TAG="BloodPressure"

    @PrimaryKey
    var keyIndex:Long=0
        get() {return field}
        set(value) {field=value}

    var userId:String=""
        get() { return field }
        set(value) { field=value }

    var systolic:Int=-1
        get() { return field }
        set(value) { field=value }

    var diastolic:Int=-1
        get() {return field}
        set(value) {field=value}

    var time:Int=-1
        get() {return field}
        set(value) {field=value}


    var note:String=""
        get(){return  field}
        set(value) {field=value}


}