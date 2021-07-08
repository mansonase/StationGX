package com.corbit.stationgx.pages.manuelinput.bloodpressure.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentTransaction
import com.corbit.stationgx.R
import com.corbit.stationgx.pages.manuelinput.bloodpressure.presenter.FragmentContract
import com.corbit.stationgx.ui.custom.calendar.CalendarUtil
import com.github.mikephil.charting.data.BarEntry
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView
import kotlinx.android.synthetic.main.calendar_select.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class BPCalendarFragment : DialogFragment(),View.OnClickListener,CalendarView.OnMonthChangeListener,CalendarView.OnCalendarSelectListener,FragmentContract.IView{

    private lateinit var duration:String
    private lateinit var calendar: java.util.Calendar
    private lateinit var arrayList: ArrayList<BarEntry>
    private lateinit var transaction: FragmentTransaction
    private lateinit var calendarView:CalendarView
    private lateinit var document:TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE,R.style.FullscreenTheme)
        //duration= arguments?.getString("duration","today")?:"today"

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val bundle=arguments
        Log.d("bpcalendarNew","oncreateview")
        if (bundle!=null){
            duration= bundle.getString("range").toString()
            CalendarUtil.duration=duration
            calendar= (bundle.getSerializable("calendar") as (ArrayList<*>)).get(0) as java.util.Calendar
            arrayList=bundle.getSerializable("barentry") as ArrayList<BarEntry>

            Log.d("bpcalendarNew","duration is $duration")
            Log.d("bpcalendarNew","calendar is ${calendar.timeInMillis}")

            if (arrayList.isNotEmpty()) {
                Log.d("bpcalendarNew", "arraylist is ${arrayList.get(0).yVals[1]}")
            }
        }

        val view=inflater.inflate(R.layout.calendar_select,container,false)
        calendarView=view.findViewById(R.id.custom_calendar)
        document=view.findViewById(R.id.calendar_documents_count)
        dialog?.window?.decorView?.systemUiVisibility=activity?.window?.decorView?.systemUiVisibility!!
        dialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("bpcalendarNew","onviewcreated")


        calendar_back.setOnClickListener(this)


        calendarView.setOnMonthChangeListener(this)
        calendarView.setSchemeDate(setSchemesFromActivity(duration,calendar,arrayList))
        calendarView.currentWeekCalendars

        calendarView.setOnCalendarSelectListener(this)
        Log.d("bp calendar", calendarView.currentWeekCalendars.toString())
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.calendar_back->{
                dismiss()
            }
        }
    }


    override fun onMonthChange(year: Int, month: Int) {
        // 左右換頁
        Log.d("on month change","$year,$month .")
    }


    override fun onCalendarOutOfRange(calendar: Calendar?) {
        Log.d("on select", calendar.toString())
    }

    override fun onCalendarSelect(calendar: Calendar?, isClick: Boolean) {
        Log.d("on select","${calendar.toString()}, $isClick")
    }

    override fun updateDocuments(string: String) {
        document.text=string
    }

    override fun updateCalendar(map: HashMap<String, Calendar>) {
        calendarView.setSchemeDate(map)
    }

    private fun setSchemesFromActivity(range:String, startDate: java.util.Calendar, arrayList: ArrayList<BarEntry>):HashMap<String,Calendar>{

        val map=HashMap<String,Calendar>()

        var calendar=Calendar()
        val days=CalendarUtil.getDays(startDate,range)


        for (i in 0 until days){
            calendar=Calendar()
            calendar.year=startDate.get(java.util.Calendar.YEAR)
            calendar.month=startDate.get(java.util.Calendar.MONTH)+1
            calendar.day=startDate.get(java.util.Calendar.DAY_OF_MONTH)

            calendar.addScheme(0x000000,range)

            map[calendar.toString()]=calendar

            startDate.add(java.util.Calendar.DAY_OF_MONTH,1)
        }

        startDate.add(java.util.Calendar.DAY_OF_MONTH,-(days+1))

        val format=SimpleDateFormat("yyyy MM dd", Locale.getDefault())
        var strDate= format.format(startDate.time)
        var date0=format.parse(strDate)!!
        var date1=Date()


        var barEntry: BarEntry?
        var cal=java.util.Calendar.getInstance()
        var calBean=java.util.Calendar.getInstance()


        val it=arrayList.iterator()
        while (it.hasNext()){
            barEntry=it.next()
            calBean.timeInMillis=(barEntry.x.toLong())*1000

            strDate=format.format(calBean.time)
            date1!=format.parse(strDate)


            if (date0.before(date1)){


                cal.time=date1
                calendar.year=cal.get(java.util.Calendar.YEAR)
                calendar.month=cal.get(java.util.Calendar.MONTH)+1
                calendar.day=cal.get(java.util.Calendar.DAY_OF_MONTH)

                val calTemp= map[calendar.toString()]!!

                calTemp.addScheme(0x000000,"event")

                map[calTemp.toString()]=calTemp

                date0=date1
            }
        }
        return map
    }
}