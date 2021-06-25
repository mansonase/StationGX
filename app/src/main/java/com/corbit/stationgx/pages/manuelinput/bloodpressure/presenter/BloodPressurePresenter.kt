package com.corbit.stationgx.pages.manuelinput.bloodpressure.presenter

import com.corbit.stationgx.data.db.manualinput.bloodpressure.BloodPressureBean
import com.corbit.stationgx.data.db.manualinput.bloodpressure.Persistence
import com.corbit.stationgx.data.db.manualinput.bloodpressure.Storage
import com.github.mikephil.charting.data.BarEntry
import io.realm.RealmResults

class BloodPressurePresenter(private val mView:Contract.IView<BloodPressureBean, BarEntry>):Contract.IPresenter,PersistenceCallbackListener<BloodPressureBean> {


    private val mStorage:Persistence<BloodPressureBean>

    init {
        mStorage=Storage(this)
    }

    override fun onBloodPressureRetrieve(bps: ArrayList<BloodPressureBean>) {
        TODO("Not yet implemented")
    }

    override fun onQueryRetrieve(bps: ArrayList<BloodPressureBean>) {
        TODO("Not yet implemented")
    }

    override fun onDayDataRetrieve(result: RealmResults<BloodPressureBean>) {

    }

    override fun onWeekDataRetrieve(result: RealmResults<BloodPressureBean>) {
        TODO("Not yet implemented")
    }

    override fun onMonthDataRetrieve(result: RealmResults<BloodPressureBean>) {
        TODO("Not yet implemented")
    }

    override fun saveData(index: Long, systolic: Int, diastolic: Int, time: Int, note: String) {

        val bean=BloodPressureBean()
        bean.keyIndex=index
        bean.systolic=systolic
        bean.diastolic=diastolic
        bean.time=time
        bean.note=note
        mStorage.sentToRealm(bean)
    }

    override fun getDayData(startTime: Int) {
        mStorage.getDayData(startTime)
    }

    override fun getWeekData(startTime: Int) {
        mStorage.getWeekData(startTime)
    }

    override fun getMonthData(startTime: Int) {
        mStorage.getMonthData(startTime)
    }

    private fun createListFromData(results: RealmResults<BloodPressureBean>){


    }
}