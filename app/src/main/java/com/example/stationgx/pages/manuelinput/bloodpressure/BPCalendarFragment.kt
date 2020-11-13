package com.example.stationgx.pages.manuelinput.bloodpressure

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentTransaction
import com.example.stationgx.R
import com.example.stationgx.ui.custom.calendar.*
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView

class BPCalendarFragment : DialogFragment(){

    private var today: FragmentToday?=null
    private var week:FragmentWeek?=null
    private var month:FragmentMonth?=null
    private var switcher: FragmentSwitcher?=null
    private lateinit var duration:String
    private lateinit var transaction: FragmentTransaction
    private lateinit var calendarView:CalendarView

    val TAG_CALENDAR_SWITCHER="switcher"
    val TAG_CALENDAR_TODAY="today"
    val TAG_CALENDAR_WEEK="week"
    val TAG_CALENDAR_MONTH="month"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE,R.style.FullscreenTheme)
        duration= arguments?.getString("duration","today")?:"today"

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.calendar,container,false)
        calendarView=view.findViewById(R.id.custom_calendar)
        dialog?.window?.decorView?.systemUiVisibility=activity?.window?.decorView?.systemUiVisibility!!
        dialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        switcher= FragmentSwitcher()
        transaction=childFragmentManager.beginTransaction()
        transaction.add(R.id.calendar_switcher, switcher!!,TAG_CALENDAR_SWITCHER)
        transaction.commit()

    }



    private fun showFragment(string:String){
        when(string){
            "today"->{
                today=childFragmentManager.findFragmentByTag(TAG_CALENDAR_TODAY) as FragmentToday
                if (today==null){
                    today= FragmentToday()
                    transaction.add(R.id.calendar_switcher, today!!,TAG_CALENDAR_TODAY).commit()
                }else{
                    transaction.hide(switcher!!).show(today!!)
                }
            }
            "week"->{
                week=childFragmentManager.findFragmentByTag(TAG_CALENDAR_WEEK) as FragmentWeek
                if (week==null){
                    week= FragmentWeek()
                    transaction.add(R.id.calendar_switcher,week!!,TAG_CALENDAR_WEEK).commit()
                }else{
                    transaction.hide(switcher!!).show(week!!)
                }
            }
            "month"->{
                month=childFragmentManager.findFragmentByTag(TAG_CALENDAR_MONTH) as FragmentMonth
                if (month==null){
                    month= FragmentMonth()
                    transaction.add(R.id.calendar_switcher,month!!,TAG_CALENDAR_MONTH).commit()
                }else{
                    transaction.hide(switcher!!).show(month!!)
                }
            }
            "switcher"->{
                switcher=childFragmentManager.findFragmentByTag(TAG_CALENDAR_SWITCHER) as FragmentSwitcher
                if (switcher==null){
                    switcher= FragmentSwitcher()
                    transaction.add(R.id.calendar_switcher,switcher!!,TAG_CALENDAR_SWITCHER).commit()
                }else{
                    transaction.hide(today!!).hide(week!!).hide(month!!).show(switcher!!)
                }
            }
        }
    }

    fun controlCalendar(){
        Log.d("bpcalendar","test function")
    }

    fun setBackgroundScheme(calendar: Calendar,duration:String){

        var days=0
        when(duration){
            "today"->days=1
            "week"->days=7
            "month"->{
                when(calendar.month){
                    1,3,5,7,8,10,12->{
                        days=31
                    }
                    2->{
                        days = if (calendar.isLeapYear){ 29 }
                        else { 28 }
                    }
                    4,6,9,11->{
                        days=30
                    }
                }
            }
        }
        val year=calendar.year
        val month=calendar.month
        var day=calendar.day
        val map= hashMapOf<String,Calendar>()


        map["mark"]=getSingleScheme(year,month,day,0x0288D1,"today")

        if (days>1){
            for (i in 0..days) {
                map["mark"] = getSingleScheme(year, month, (day + i), 0xF9EBEC, "range")
            }
        }

        map["mark"]=getSingleScheme(year,month,4,0x229AD7,"record")
        map["mark"]=getSingleScheme(year,month,5,0x229AD7,"record")
        map["mark"]=getSingleScheme(year,month,6,0x229AD7,"record")
        map["mark"]=getSingleScheme(year,month,7,0x229AD7,"record")
        map["mark"]=getSingleScheme(year,month,8,0x229AD7,"record")
        map["mark"]=getSingleScheme(year,month,9,0x229AD7,"record")
        map["mark"]=getSingleScheme(year,month,10,0x229AD7,"record")
        map["mark"]=getSingleScheme(year,month,11,0x229AD7,"record")

        calendarView.setSchemeDate(map)
    }

    private fun getSingleScheme(year:Int,month:Int,day:Int,color:Int,text:String):Calendar{
        val calendar=Calendar()
        calendar.year=year
        calendar.month=month
        calendar.day=day
        calendar.schemeColor=color
        calendar.scheme=text
        return calendar
    }
}