package com.corbit.stationgx.pages.manuelinput.bloodpressure.presenter

import android.util.Log
import com.corbit.stationgx.data.db.manualinput.bloodpressure.BloodPressureBean
import com.corbit.stationgx.data.db.manualinput.bloodpressure.Persistence
import com.corbit.stationgx.data.db.manualinput.bloodpressure.Storage
import com.corbit.stationgx.pages.manuelinput.bloodpressure.view.BloodPressureActivity
import com.corbit.stationgx.pages.manuelinput.bloodpressure.view.Entity
import com.corbit.stationgx.utils.Utils
import com.github.mikephil.charting.data.BarEntry
import io.realm.RealmResults
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class BloodPressurePresenter(private val mView: BloodPressureActivity):Contract.IPresenter,PersistenceCallbackListener<BloodPressureBean> {


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

        val barEntryList=ArrayList<BarEntry>()
        val entityList=ArrayList<Entity>()

        val calendar=Calendar.getInstance()
        val timeFormat=SimpleDateFormat("HH:mm", Locale.getDefault())

        val it=result.iterator()

        while (it.hasNext()){
            val bpBean=it.next()

            val bloodPressure= floatArrayOf(bpBean.diastolic.toFloat(),(bpBean.systolic-bpBean.diastolic).toFloat())
            barEntryList.add(BarEntry(bpBean.time.toFloat(),bloodPressure))

            val milliseconds=(bpBean.time.toLong()*1000)
            calendar.timeInMillis=milliseconds

            val date=Utils.createListDate(calendar)
            val time=timeFormat.format(calendar.time)

            val strBloodPressure="${bpBean.systolic} / ${bpBean.diastolic}"
            val note=bpBean.note

            val abnormal=bpBean.systolic>140

            entityList.add(Entity(date,time,strBloodPressure,note,abnormal))

        }

        Log.d("presenter","$barEntryList")

        mView.updateChart(barEntryList)

        mView.updateTable(entityList)
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

    private fun createListFromData(results: RealmResults<BloodPressureBean>):ArrayList<Entity>{

        val list=ArrayList<Entity>()

        val timeFormat=SimpleDateFormat("HH:mm", Locale.getDefault())
        val calendar=Calendar.getInstance()
        val it=results.iterator()

        while (it.hasNext()){
            val bpBean=it.next()

            val milliseconds=(bpBean.time.toLong()*1000)
            calendar.timeInMillis=milliseconds

            val date=Utils.createListDate(calendar)
            val time=timeFormat.format(calendar.time)

            val bloodPressure="${bpBean.systolic} / ${bpBean.diastolic}"
            val note=bpBean.note

            val abnormal= bpBean.systolic>140

            list.add(Entity(date,time,bloodPressure,note,abnormal))

        }

        return list
    }
}