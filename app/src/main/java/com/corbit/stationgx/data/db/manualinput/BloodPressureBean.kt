package com.corbit.stationgx.data.db.manualinput

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class BloodPressureBean : RealmObject() {
    //private val TAG="BloodPressure"

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

    var time:String=""
        get() {return field}
        set(value) {field=value}

    var date:String=""
        get() {return field}
        set(value) {field=value}

    var note:String=""
        get(){return  field}
        set(value) {field=value}

    var year:Int=-1
        get() {return field}
        set(value) {field=value}

    var month:Int=-1
        get() {return field}
        set(value) {field=value}

    var week:Int=-1
        get() {return field}
        set(value) {field=value}

    var day:Int=-1
        get() {return field}
        set(value) {field=value}

    var hour:Int=-1
        get() {return field}
        set(value) {field=value}

    var minute:Int=-1
        get() {return field}
        set(value) {field=value}

    var sec:Int=-1
        get() {return field}
        set(value) {field=value}

    init {
        userId=""
        time=""
        date=""
        note=""
        systolic=-1
        diastolic=-1
    }

}