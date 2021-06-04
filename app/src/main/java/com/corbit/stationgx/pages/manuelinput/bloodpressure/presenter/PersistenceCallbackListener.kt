package com.corbit.stationgx.pages.manuelinput.bloodpressure.presenter

import com.corbit.stationgx.pages.manuelinput.model.BloodPressure

interface PersistenceCallbackListener {

    fun onBloodPressureRetrieve(bps:List<BloodPressure>)

    fun onQueryRetrieve(bps:List<BloodPressure>)


}