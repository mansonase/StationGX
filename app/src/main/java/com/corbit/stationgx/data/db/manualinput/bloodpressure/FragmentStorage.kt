package com.corbit.stationgx.data.db.manualinput.bloodpressure

import com.corbit.stationgx.pages.manuelinput.bloodpressure.presenter.FragmentPersistenceCallbackListener
import io.realm.Realm
import java.sql.Timestamp
import java.util.*

class FragmentStorage(private val fragmentPersistenceCallbackListener: FragmentPersistenceCallbackListener<BloodPressureBean>):FragmentPersistence {

    override fun getDayData(startTime: Int) {
        val realm=Realm.getDefaultInstance()

        val calendar=Calendar.getInstance()
        calendar.timeInMillis=startTime.toLong()*1000
        calendar.add(Calendar.DAY_OF_MONTH,1)
        val endTime=(calendar.timeInMillis/1000).toInt()

        val results=realm.where(BloodPressureBean::class.java)
                .greaterThanOrEqualTo("time",startTime)
                .lessThan("time",endTime)
                .findAll()

        fragmentPersistenceCallbackListener.onDayDataRetrieve(results)

        realm.close()
    }

    override fun getWeekData(startTime: Int) {
        val realm=Realm.getDefaultInstance()

        val calendar=Calendar.getInstance()
        calendar.timeInMillis=startTime.toLong()*1000
        calendar.add(Calendar.DAY_OF_MONTH,7)
        val endTime=(calendar.timeInMillis/1000).toInt()

        val results=realm.where(BloodPressureBean::class.java)
                .greaterThanOrEqualTo("time",startTime)
                .lessThan("time",endTime)
                .findAll()

        fragmentPersistenceCallbackListener.onWeekDataRetrieve(results)

        realm.close()
    }

    override fun getMonthData(startTime: Int) {
        val realm=Realm.getDefaultInstance()

        val calendar=Calendar.getInstance()
        calendar.timeInMillis=startTime.toLong()*1000
        calendar.add(Calendar.MONTH,1)
        val endTime=(calendar.timeInMillis/1000).toInt()

        val results=realm.where(BloodPressureBean::class.java)
                .greaterThanOrEqualTo("time",startTime)
                .lessThan("time",endTime)
                .findAll()

        fragmentPersistenceCallbackListener.onMonthDataRetrieve(results)
        realm.close()
    }
}