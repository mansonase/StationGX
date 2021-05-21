package com.corbit.stationgx.pages.manuelinput.bloodpressure

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentTransaction
import com.corbit.stationgx.R
import com.corbit.stationgx.ui.custom.calendar.bp.FragmentMonth
import com.corbit.stationgx.ui.custom.calendar.bp.FragmentSwitcher
import com.corbit.stationgx.ui.custom.calendar.bp.FragmentToday
import com.corbit.stationgx.ui.custom.calendar.bp.FragmentWeek
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView

class BPCalendarFragment : DialogFragment(){

    private var today: FragmentToday?=null
    private var week: FragmentWeek?=null
    private var month: FragmentMonth?=null
    private var switcher: FragmentSwitcher?=null
    private lateinit var duration:String
    private lateinit var transaction: FragmentTransaction
    private lateinit var calendarView:CalendarView

    private val TAG_CALENDAR_SWITCHER="switcher"
    private val TAG_CALENDAR_TODAY="today"
    private val TAG_CALENDAR_WEEK="week"
    private val TAG_CALENDAR_MONTH="month"


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
            Log.d("bpcalendarNew","duration is $duration")
        }

        val view=inflater.inflate(R.layout.calendar_bp,container,false)
        calendarView=view.findViewById(R.id.custom_calendar)
        dialog?.window?.decorView?.systemUiVisibility=activity?.window?.decorView?.systemUiVisibility!!
        dialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("bpcalendarNew","onviewcreated")
        switcher= FragmentSwitcher()
        today= FragmentToday()
        week= FragmentWeek()
        month= FragmentMonth()
        transaction=childFragmentManager.beginTransaction()
        transaction.add(R.id.calendar_switcher,today!!,TAG_CALENDAR_TODAY)
        transaction.add(R.id.calendar_switcher,week!!,TAG_CALENDAR_WEEK)
        transaction.add(R.id.calendar_switcher,month!!,TAG_CALENDAR_MONTH)
        transaction.hide(today!!)
        transaction.hide(week!!)
        transaction.hide(month!!)
        transaction.add(R.id.calendar_switcher, switcher!!,TAG_CALENDAR_SWITCHER)
        transaction.commit()

        val calendar=Calendar()
        calendar.year=2020
        calendar.month=11
        calendar.day=23
        setBackgroundScheme(calendar,duration)

    }

    private fun showFragment(fragment:String,duration: String){
        when(fragment){
            "today"->{

                (activity as BloodPressureActivity).selectDuration("today")
                dismiss()

                /*
                transaction=childFragmentManager.beginTransaction()
                if (week!=null){
                    transaction.hide(week!!)
                }
                if (month!=null){
                    transaction.hide(month!!)
                }
                if (switcher!=null){
                    transaction.hide(switcher!!)
                }
                //today=childFragmentManager.findFragmentByTag(TAG_CALENDAR_TODAY) as FragmentToday
                if (today==null){
                    today= FragmentToday()
                    transaction.add(R.id.calendar_switcher, today!!,TAG_CALENDAR_TODAY).commit()
                }else{
                    transaction.show(today!!)
                }
                transaction.commit()

                 */
            }
            "week"->{
                (activity as BloodPressureActivity).selectDuration("week")
                dismiss()
                /*
                transaction=childFragmentManager.beginTransaction()
                if (today!=null){
                    transaction.hide(today!!)
                }
                if (month!=null){
                    transaction.hide(month!!)
                }
                if (switcher!=null){
                    transaction.hide(switcher!!)
                }
                //week=childFragmentManager.findFragmentByTag(TAG_CALENDAR_WEEK) as FragmentWeek
                if (week==null){
                    week= FragmentWeek()
                    transaction.add(R.id.calendar_switcher,week!!,TAG_CALENDAR_WEEK).commit()
                }else{
                    transaction.show(week!!)
                }
                transaction.commit()

                 */
            }
            "month"->{
                (activity as BloodPressureActivity).selectDuration("month")
                dismiss()
                /*
                transaction=childFragmentManager.beginTransaction()
                if (today!=null){
                    transaction.hide(today!!)
                }
                if (week!=null){
                    transaction.hide(week!!)
                }
                if (switcher!=null){
                    transaction.hide(switcher!!)
                }
                //month=childFragmentManager.findFragmentByTag(TAG_CALENDAR_MONTH) as FragmentMonth
                if (month==null){
                    month= FragmentMonth()
                    transaction.add(R.id.calendar_switcher,month!!,TAG_CALENDAR_MONTH).commit()
                }else{
                    transaction.show(month!!)
                }
                transaction.commit()

                 */
            }
            "switcher"->{
                transaction=childFragmentManager.beginTransaction()

                when(duration){
                    "today"->{
                        transaction.hide(switcher!!).hide(week!!).hide(month!!).show(today!!)
                    }
                    "week"->{
                        transaction.hide(switcher!!).hide(today!!).hide(month!!).show(week!!)
                    }
                    "month"->{
                        transaction.hide(switcher!!).hide(today!!).hide(week!!).show(month!!)
                    }
                }

/*
                if (today!=null){
                    transaction.hide(today!!)
                }
                if (week!=null){
                    transaction.hide(week!!)
                }
                if (month!=null){
                    transaction.hide(month!!)
                }
                //switcher=childFragmentManager.findFragmentByTag(TAG_CALENDAR_SWITCHER) as FragmentSwitcher
                if (switcher==null){
                    switcher= FragmentSwitcher()
                    val bundle=Bundle()
                    bundle.putString("range",duration)
                    switcher!!.arguments=bundle
                    transaction.add(R.id.calendar_switcher,switcher!!,TAG_CALENDAR_SWITCHER)
                    Log.d("bpcalendarfre","switcher is null")
                }else{

                    if (switcher!!.isAdded){
                        transaction.show(switcher!!)
                    }else{
                        transaction.add(R.id.calendar_switcher,switcher!!,TAG_CALENDAR_SWITCHER)
                    }
                    switcher!!.fragmentSetDuration(duration)
                    transaction.show(switcher!!)
                    Log.d("bpcalendarfre","switcher is not null")
                }

 */
                transaction.commit()

            }
        }
    }

    fun controlCalendar(change:String,duration: String){
        Log.d("bpcalendar","test function")
        showFragment(change,duration)
    }

    fun controlSwitcher(duration: String){
        transaction=childFragmentManager.beginTransaction()
        
        switcher?.fragmentSetDuration(duration)
        val bundle=Bundle()
        bundle.putString("range",duration)
        (childFragmentManager.findFragmentByTag(TAG_CALENDAR_SWITCHER))?.arguments=bundle

        transaction.hide(week!!).hide(month!!).hide(today!!).show(switcher!!)
        
        transaction.commit()
    }

    private fun setBackgroundScheme(calendar: Calendar, duration:String){

        var days=0
        var daysOfMonth=0

        when(duration){
            "today"->{
                days=1
            }
            "week"->{
                days=7
                when(calendar.month){
                    1,3,5,7,8,10,12->{
                        daysOfMonth=31
                    }
                    2->{
                        daysOfMonth=if (calendar.isLeapYear){ 29 }
                        else { 28 }
                    }
                    4,6,9,11->{
                        daysOfMonth=30
                    }
                }

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


        var map= hashMapOf<String,Calendar>()

        var year=calendar.year
        var month=calendar.month
        var day: Int

        //如果days==1,表示就是系統日期的今天
        when {
            days==1 -> {  // today , do nothing

            }
            days==7 -> {  // week

                day=calendar.day

                for (i in 0 until days) {

                    if (day+i>daysOfMonth){// 有over這個月

                        var newDay:Int
                        var newMonth: Int
                        var newYear=0
                        if (month+1>12){//有over今年

                            newMonth=1
                            newYear=year+1
                            newDay=day+i-daysOfMonth
                        }else{//沒有over今年

                            newYear=year
                            newMonth=month+1
                            newDay=day+i-daysOfMonth
                        }


                        val cal = getSingleScheme(newYear, newMonth, newDay,  "range")
                        map[cal.toString()]=cal
                        Log.d("setbackgroundscheme","$year, $month, $day+$i, ${cal.toString()}")

                    } else {// week沒有過這個月
                        Log.d("setbackgroundscheme","$year, $month, $day+$i")
                        val cal= getSingleScheme(year, month, (day + i),  "range")
                        map[cal.toString()]=cal
                    }
                }
            }
            days>7 -> { // days超過7天, 表示是month了

                day=calendar.day

                for (i in 0 until days){

                    day += i

                    val cal=getSingleScheme(year,month,day,"range")
                    map[cal.toString()]=cal
                }
            }
        }
        Log.d("bpcalendar","map size is ${map.size}, before, ${getSingleScheme(year,month,4,"record").toString()}")



        // for example mark some date to show record
        map=setEventsScheme(map,getSingleScheme(calendar.year,calendar.month,4,"record"))
        map=setEventsScheme(map,getSingleScheme(calendar.year,calendar.month,5,"record"))
        map=setEventsScheme(map,getSingleScheme(calendar.year,calendar.month,6,"record"))
        map=setEventsScheme(map,getSingleScheme(calendar.year,calendar.month,7,"record"))
        map=setEventsScheme(map,getSingleScheme(calendar.year,calendar.month,8,"record"))
        map=setEventsScheme(map,getSingleScheme(calendar.year,calendar.month,9,"record"))
        map=setEventsScheme(map,getSingleScheme(calendar.year,calendar.month,10,"record"))
        map=setEventsScheme(map,getSingleScheme(calendar.year,calendar.month,11,"record"))


        for (m in map){
            Log.d("bpcalendarrr","it is ${m.key}")
        }
        Log.d("bpcalendar","map size is ${map.size},after")


        calendarView.setSchemeDate(map)

    }

    private fun getSingleScheme(year:Int,month:Int,day:Int,text:String):Calendar{
        val calendar=Calendar()
        calendar.year=year
        calendar.month=month
        calendar.day=day

        calendar.schemeColor=0xFFFFFF

        if (text=="none"||text=="range"){
            calendar.addScheme(0xFFFFFF,text)
        }else{
            Log.d("bpcalendarff", "$text,$year year,  $month month, $day day")
            calendar.addScheme(0x229AD7,text)

        }
        Log.d("setEventsScheme",calendar.toString())
        return calendar
    }


    private fun setEventsScheme(map: HashMap<String,Calendar>,calendar: Calendar):HashMap<String,Calendar>{

        val date=calendar.toString()

        var cal=Calendar()

        if (map.containsKey(date)){

            cal.year=calendar.year
            cal.month=calendar.month
            cal.day=calendar.day
            cal.schemeColor=0xFFFFFF
            cal.addScheme(0x000000,"range")
            cal.addScheme(0x000000,"record")

        }else{
            cal=getSingleScheme(calendar.year,calendar.month,calendar.day,"record")

        }
        Log.d("setEventsScheme",cal.toString())
        map[date]=cal

        return map
    }
}