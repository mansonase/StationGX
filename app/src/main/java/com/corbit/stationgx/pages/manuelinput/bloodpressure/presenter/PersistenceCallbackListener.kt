package com.corbit.stationgx.pages.manuelinput.bloodpressure.presenter

import com.corbit.stationgx.data.db.manualinput.bloodpressure.BloodPressureBean
import io.realm.RealmResults

interface PersistenceCallbackListener<T> {

    fun onBloodPressureRetrieve(bps:ArrayList<BloodPressureBean>)

    fun onQueryRetrieve(bps:ArrayList<BloodPressureBean>)

    fun onDayDataRetrieve(result: RealmResults<T>)

    fun onWeekDataRetrieve(result: RealmResults<T>)

    fun onMonthDataRetrieve(result: RealmResults<T>)
}