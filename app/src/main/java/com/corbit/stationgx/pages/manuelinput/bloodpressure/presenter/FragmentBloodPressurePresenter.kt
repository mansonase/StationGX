package com.corbit.stationgx.pages.manuelinput.bloodpressure.presenter

import android.graphics.Color
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import com.corbit.stationgx.R
import com.corbit.stationgx.data.db.manualinput.bloodpressure.BloodPressureBean
import com.corbit.stationgx.data.db.manualinput.bloodpressure.FragmentPersistence
import com.corbit.stationgx.data.db.manualinput.bloodpressure.FragmentStorage
import com.corbit.stationgx.pages.manuelinput.bloodpressure.view.BPCalendarFragment
import com.corbit.stationgx.ui.custom.calendar.CalendarUtil
import com.haibin.calendarview.Calendar
import io.realm.RealmResults
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class FragmentBloodPressurePresenter(private val mView:BPCalendarFragment):FragmentContract.IPresenter,FragmentPersistenceCallbackListener<BloodPressureBean> {

    private val mStorage:FragmentPersistence

    init {
        mStorage=FragmentStorage(this)
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

    override fun onDayDataRetrieve(result: RealmResults<BloodPressureBean>) {


        val count=result.size

        val builder=SpannableStringBuilder()
        builder.append(mView.getString(R.string.you_have))
        builder.append(" ")
        builder.append(styleString(count.toString()))
        builder.append(" ")
        builder.append(mView.getString(R.string.document))
        builder.append("\n")
        builder.append(mView.getString(R.string.on_the_date))
        mView.updateDocuments(builder)
    }

    override fun onWeekDataRetrieve(result: RealmResults<BloodPressureBean>) {
        val count=result.size

        val builder=SpannableStringBuilder()
        builder.append(mView.getString(R.string.you_have))
        builder.append(" ")
        builder.append(styleString(count.toString()))
        builder.append(" ")
        builder.append(mView.getString(R.string.document))
        builder.append("\n")
        builder.append(mView.getString(R.string.on_this_week))
        mView.updateDocuments(builder)
    }

    override fun onMonthDataRetrieve(result: RealmResults<BloodPressureBean>) {


        val count=result.size

        val builder=SpannableStringBuilder()
        builder.append(mView.getString(R.string.you_have))
        builder.append(" ")
        builder.append(styleString(count.toString()))
        builder.append(" ")
        builder.append(mView.getString(R.string.document))
        builder.append("\n")
        when(CalendarUtil.duration){
            CalendarUtil.day->{
                builder.append(mView.getString(R.string.on_the_date))
            }
            CalendarUtil.week->{
                builder.append(mView.getString(R.string.on_this_week))
            }
            else->{
                builder.append(mView.getString(R.string.on_this_month))
            }
        }
        mView.updateDocuments(builder)


        val format=SimpleDateFormat("yyyy MM dd", Locale.getDefault())
        val map=HashMap<String,Calendar>()

        if (result[0]==null)return

        val startTime= (result[0]!!.time).toLong().times(1000)

        val calendar0=java.util.Calendar.getInstance()
        calendar0.timeInMillis=startTime
        calendar0.add(java.util.Calendar.DAY_OF_MONTH,-1)

        val stringDate0=format.format(calendar0.time)
        val date0=format.parse(stringDate0)!!
        //第一筆資料的前一天
        calendar0.time=date0

        val it=result.iterator()
        while (it.hasNext()){

            val bean=it.next()
            val calendar1=java.util.Calendar.getInstance()
            calendar1.timeInMillis=(bean.time.toLong())*1000
            val stringDate1=format.format(calendar1.time)
            val date1=format.parse(stringDate1)!!
            calendar1.time=date1

            if (calendar0.before(calendar1)){

                val calendar=Calendar()
                calendar.year=calendar1.get(java.util.Calendar.YEAR)
                calendar.month=calendar1.get(java.util.Calendar.MONTH)+1
                calendar.day=calendar1.get(java.util.Calendar.DAY_OF_MONTH)

                calendar.addScheme(0x000000,"event")

                map[calendar.toString()] = calendar

                calendar0.time=date1
            }
        }
        mView.updateCalendar(map)
    }


    private fun styleString(text:String):SpannableString{
        val sbs=SpannableString(text)
        val foreColor=ForegroundColorSpan(mView.resources.getColor(R.color.word_blue,null))
        sbs.setSpan(foreColor,0,text.length,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        return sbs
    }
}