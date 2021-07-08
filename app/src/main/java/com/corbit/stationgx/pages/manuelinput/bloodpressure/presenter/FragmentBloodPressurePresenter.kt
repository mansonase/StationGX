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
import io.realm.RealmResults

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
        builder.append(styleString(count.toString()))
        builder.append(mView.getString(R.string.document))
        builder.append("\n")
        builder.append(mView.getString(R.string.on_the_date))
        mView.updateDocuments(builder.toString())
    }

    override fun onWeekDataRetrieve(result: RealmResults<BloodPressureBean>) {
        val count=result.size

        val builder=SpannableStringBuilder()
        builder.append(mView.getString(R.string.you_have))
        builder.append(styleString(count.toString()))
        builder.append(mView.getString(R.string.document))
        builder.append("\n")
        builder.append(mView.getString(R.string.on_this_week))
        mView.updateDocuments(builder.toString())
    }

    override fun onMonthDataRetrieve(result: RealmResults<BloodPressureBean>) {
        val count=result.size

        val builder=SpannableStringBuilder()
        builder.append(mView.getString(R.string.you_have))
        builder.append(styleString(count.toString()))
        builder.append(mView.getString(R.string.document))
        builder.append("\n")
        builder.append(mView.getString(R.string.on_this_month))
        mView.updateDocuments(builder.toString())

        TODO("Not yet implemented")
    }


    private fun styleString(text:String):SpannableString{
        val sbs=SpannableString(text)
        val foreColor=ForegroundColorSpan(mView.resources.getColor(R.color.word_blue,null))
        sbs.setSpan(foreColor,0,text.length,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        return sbs
    }
}