package com.corbit.stationgx.data.db.manualinput.bloodpressure

import android.util.Log
import com.corbit.stationgx.pages.manuelinput.bloodpressure.presenter.PersistenceCallbackListener
import io.realm.Realm
import java.sql.Timestamp
import java.util.*

class Storage(private val persistenceCallbackListener: PersistenceCallbackListener<BloodPressureBean>) :Persistence<BloodPressureBean>{

    override fun sentToRealm(data: BloodPressureBean) {
        val realm=Realm.getDefaultInstance()
        realm.run {
            beginTransaction()
            copyToRealm(data)
            commitTransaction()
        }
        realm.close()
        Log.d("nonononono","${data.systolic}")
    }

    override fun queryRealm(query: String) {
        TODO("Not yet implemented")
    }

    override fun updatePerson(data: BloodPressureBean) {
        TODO("Not yet implemented")
    }


    override fun getDayData(startTime: Int) {
        val realm=Realm.getDefaultInstance()

        val calendar=Calendar.getInstance()
        val timestamp=Timestamp(startTime.toLong()*1000)
        calendar.time=timestamp
        calendar.add(Calendar.DAY_OF_MONTH,1)
        val endTime= (calendar.timeInMillis/1000).toInt()



        val results=realm.where(BloodPressureBean::class.java)
                .greaterThanOrEqualTo("time",startTime)
                .lessThan("time",endTime)
                .findAll()

        persistenceCallbackListener.onDayDataRetrieve(results)

        realm.close()
    }

    override fun getWeekData(startTime: Int) {
        val realm=Realm.getDefaultInstance()

        val calendar=Calendar.getInstance()
        val timestamp=Timestamp(startTime.toLong()*1000)
        calendar.time=timestamp
        calendar.add(Calendar.DAY_OF_MONTH,7)
        val endTime=(calendar.timeInMillis/1000).toInt()

        val results=realm.where(BloodPressureBean::class.java)
                .greaterThanOrEqualTo("time",startTime)
                .lessThan("time",endTime)
                .findAll()

        persistenceCallbackListener.onWeekDataRetrieve(results)

        realm.close()
    }

    override fun getMonthData(startTime: Int) {
        val realm=Realm.getDefaultInstance()

        val calendar=Calendar.getInstance()
        val timestamp=Timestamp(startTime.toLong()*1000)
        calendar.time=timestamp
        calendar.add(Calendar.MONTH,1)
        val endTime=(calendar.timeInMillis/1000).toInt()

        val results=realm.where(BloodPressureBean::class.java)
                .greaterThanOrEqualTo("time",startTime)
                .lessThan("time",endTime)
                .findAll()

        persistenceCallbackListener.onMonthDataRetrieve(results)

        realm.close()
    }
}