package com.example.stationgx.data.db.manualinput

import io.realm.RealmObject

open class BloodPressureBean : RealmObject() {
    private val TAG="BloodPressure"

    private var userId:String=""
        get() { return field }
        set(value) { field=value }

    private var systolic:Int=-1
        get() { return field }
        set(value) { field=value }

    private var diastolic:Int=-1
        get() {return field}
        set(value) {field=value}

    private var time:String=""
        get() {return field}
        set(value) {field=value}

    private var date:String=""
        get() {return field}
        set(value) {field=value}

    private var note:String=""
        get(){return  field}
        set(value) {field=value}

    private var year:Int=-1
        get() {return field}
        set(value) {field=value}

    private var month:Int=-1
        get() {return field}
        set(value) {field=value}

    private var week:Int=-1
        get() {return field}
        set(value) {field=value}

    private var day:Int=-1
        get() {return field}
        set(value) {field=value}

    private var hour:Int=-1
        get() {return field}
        set(value) {field=value}

    private var minute:Int=-1
        get() {return field}
        set(value) {field=value}

    private var sec:Int=-1
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