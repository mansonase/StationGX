package com.corbit.stationgx.pages.manuelinput.model

import com.corbit.stationgx.data.db.manualinput.models.BloodPressureModels

data class BloodPressure(var list:List<BloodPressureModels>){

    var systolicList:List<Int>?=null
    var diastolicList:List<Int>?=null



}
