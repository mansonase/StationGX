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

        val calendar=Calendar()
        calendar.year=2020
        calendar.month=10
        calendar.day=20
        setBackgroundScheme(calendar,"today")

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

    private fun setBackgroundScheme(calendar: Calendar, duration:String){

        var days=0
        when(duration){
            "today"->{
                days=1
                setTodayScheme(calendar)
            }
            "week"->{
                days=7
            }
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



/*

        val map= hashMapOf<String,Calendar>()

        var year=calendar.year
        var month=calendar.month
        var day: Int


        // 用原生API找出現在的日期
        val calendarReal=java.util.Calendar.getInstance()


        //如果days==1,表示就是系統日期的今天
        if (days==1){
            map["mark"]=getSingleScheme(
                                calendarReal.get(java.util.Calendar.YEAR)+0,
                              calendarReal.get(java.util.Calendar.MONTH)+1,
                                 calendarReal.get(java.util.Calendar.DAY_OF_MONTH)+0,
                                        "today")

        }
        //days!=0 表示有範圍, 不止1天而已
        else{

            day=calendar.day

            if (days>1){

                // if week or month
                for (i in 0..days) {

                    if (day+i>days){// week 有over這個月

                        if (month+1>12){//有over今年

                            month=1
                            year+=1
                            day=1
                        }else{//沒有over今年

                            month+=1
                            day=1
                        }

                        map["mark"] = getSingleScheme(year, month, (day + i-days),  "range")


                    }else {// week沒有過這個月
                        map["mark"] = getSingleScheme(year, month, (day + i),  "range")
                    }
                }
            }

            if (calendar.year == calendarReal.get(java.util.Calendar.YEAR)&&calendar.month==(calendarReal.get(java.util.Calendar.MONTH)+1)){
                // 判斷現在展示的這個月曆 是今年&&這個月, 如果是的話 要加 today的mark
                //                                      如果是別的月份 就不用加today's mark

                day=calendarReal.get(java.util.Calendar.DAY_OF_MONTH)
                map["mark"]=getSingleScheme(year,month,day,"both")
            }


        }

        // for example mark some date to show record
        map["mark"]=getSingleScheme(year,month,4,"record")
        map["mark"]=getSingleScheme(year,month,5,"record")
        map["mark"]=getSingleScheme(year,month,6,"record")
        map["mark"]=getSingleScheme(year,month,7,"record")
        map["mark"]=getSingleScheme(year,month,8,"record")
        map["mark"]=getSingleScheme(year,month,9,"record")
        map["mark"]=getSingleScheme(year,month,10,"record")
        map["mark"]=getSingleScheme(year,month,11,"record")

        calendarView.setSchemeDate(map)

 */
    }

    private fun getSingleScheme(year:Int,month:Int,day:Int,text:String):Calendar{
        val calendar=Calendar()
        calendar.year=year
        calendar.month=month
        calendar.day=day

        if (text=="today"||text=="range"||text=="both"){
            calendar.scheme=text
        }else{
            Log.d("bpcalendarff", "$text,$year year,  $month month, $day day")
            //calendar.addScheme(0x229AD7,text)
            calendar.scheme=text
        }
        //calendar.scheme=text
        return calendar
    }


    private fun setTodayScheme(calendar: Calendar){

        val map= hashMapOf<String,Calendar>()
        val calendar=getSingleScheme(calendar.year,calendar.month,calendar.day,"record")

        map.put("mark",calendar)


        calendarView.setSchemeDate(map)
        calendarView.invalidate()

    }
    private fun setWeekScheme(calendar: Calendar, days:Int){

    }
    private fun setMonthScheme(calendar: Calendar, days: Int){
    }
}