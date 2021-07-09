package com.corbit.stationgx.ui.custom.calendar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.Log
import androidx.core.content.res.ResourcesCompat
import com.corbit.stationgx.R
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.MonthView

class CalendarMonth(context: Context) :MonthView(context) {


    private val lastMonthDates=Paint(Paint.ANTI_ALIAS_FLAG)
    private val nextMonthDates=Paint(Paint.ANTI_ALIAS_FLAG)
    private val currentMonthPassedDates=Paint(Paint.ANTI_ALIAS_FLAG)
    private val currentMonthComingDates=Paint(Paint.ANTI_ALIAS_FLAG)
    private val paintToday=Paint(Paint.ANTI_ALIAS_FLAG)
    private val paintTodayCircle=Paint(Paint.ANTI_ALIAS_FLAG)
    private val paintEventDates=Paint(Paint.ANTI_ALIAS_FLAG)
    private val paintRangeDates=Paint(Paint.ANTI_ALIAS_FLAG)

    private val calendarNow=java.util.Calendar.getInstance()
    private val calendarCompare=java.util.Calendar.getInstance()

    init {
        val font=ResourcesCompat.getFont(context, R.font.roboto_bold)

        lastMonthDates.color= resources.getColor(android.R.color.transparent, null)

        nextMonthDates.color=resources.getColor(R.color.frame_grey, null)
        nextMonthDates.textSize=24f
        nextMonthDates.typeface=font

        currentMonthPassedDates.color=resources.getColor(R.color.word_black, null)
        currentMonthPassedDates.textSize=24f
        currentMonthPassedDates.typeface = font


        currentMonthComingDates.color=resources.getColor(R.color.icon_grey, null)
        currentMonthComingDates.textSize=24f
        currentMonthComingDates.typeface=font

        paintToday.color=resources.getColor(R.color.main_white, null)
        paintToday.textSize=24f
        paintToday.typeface=font

        paintTodayCircle.color=resources.getColor(R.color.word_blue, null)
        paintTodayCircle.style=Paint.Style.FILL

        paintEventDates.color=resources.getColor(R.color.border_blue, null)
        paintEventDates.style=Paint.Style.STROKE
        paintEventDates.strokeWidth=2f


        Log.d("init", "testingggg")
        paintRangeDates.color=resources.getColor(R.color.background_pink, null)
        paintRangeDates.style=Paint.Style.FILL
    }

    override fun onDrawSelected(canvas: Canvas?, calendar: Calendar?, x: Int, y: Int, hasScheme: Boolean): Boolean {
        val centerX=x+mItemWidth/2
        val centerY=y+mItemHeight/2

        Log.d("onSelect","in onDrawSelected part ${calendar.toString()}, $x, $y, $mItemWidth, $mItemHeight")

        when(CalendarUtil.duration){
            CalendarUtil.day->{
                canvas?.drawCircle((centerX+mItemHeight/4).toFloat(),centerY.toFloat(),(mItemHeight/2).toFloat(),paintRangeDates)
            }
            CalendarUtil.week->{
                if (calendar==null)return true
                val dayDiff=CalendarUtil.getWeekFromCalendar(calendar)-1

                val xStart=x-dayDiff*mItemWidth
                val xEnd=x+(7-dayDiff)*mItemWidth

                val yTop=y
                val yBottom=y+mItemHeight
                canvas?.drawRect(xStart.toFloat(), yTop.toFloat(), xEnd.toFloat(), yBottom.toFloat(),paintRangeDates)

            }
            CalendarUtil.month->{

            }
        }

        return true
    }

    override fun onDrawScheme(canvas: Canvas?, calendar: Calendar?, x: Int, y: Int) {
        val centerX=x+mItemWidth/2
        val centerY=y+mItemHeight/2

        if (calendar==null){
            return
        }


        val schemes= calendar.schemes
        Log.d("calendarmonth"," ${calendar.day}, size is ${schemes.size}, 0=${schemes[0].scheme}")


        when(schemes[0].scheme){
            CalendarUtil.day->{
                canvas?.drawCircle((centerX+mItemHeight/4).toFloat(),centerY.toFloat(),(mItemHeight/2).toFloat(),paintRangeDates)
            }
            CalendarUtil.week->{

                val dayOfWeek=CalendarUtil.getWeekFromCalendar(calendar)
                if (dayOfWeek==1){// if this day is monday, draw circle and half rect
                    canvas?.drawCircle((centerX+mItemHeight/4).toFloat(),centerY.toFloat(),(mItemHeight/2).toFloat(), paintRangeDates)
                    canvas?.drawRect((centerX+mItemHeight/4).toFloat(),y.toFloat(),(x+mItemWidth).toFloat(),(y+mItemHeight).toFloat(),paintRangeDates)
                }else if (dayOfWeek==0){// if this day is sunday, draw circle and half rect
                    canvas?.drawCircle((centerX+mItemHeight/4).toFloat(),centerY.toFloat(),(mItemHeight/2).toFloat(),paintRangeDates)
                    canvas?.drawRect(x.toFloat(),y.toFloat(),(centerX+mItemWidth/4).toFloat(),(y+mItemHeight).toFloat(),paintRangeDates)
                }else{// this day is tuesday to saturday, draw rect
                    canvas?.drawRect(x.toFloat(),y.toFloat(),(x+mItemWidth).toFloat(),(y+mItemHeight).toFloat(),paintRangeDates)
                }
            }
            CalendarUtil.month->{
                canvas?.drawRect(x.toFloat(),y.toFloat(),(x+mItemWidth).toFloat(),(y+mItemHeight).toFloat(),paintRangeDates)
            }
        }
        if (schemes.size>1) {
            if (schemes[1].scheme=="event"){
                Log.d("calendarmonth","${schemes[1].scheme}, $calendar")
                canvas?.drawCircle((centerX+mItemHeight/4).toFloat(),centerY.toFloat(),22f,paintEventDates)
            }
        }


        /*
        if (schemes[0].scheme=="record"){
            Log.d("calendarmonth","scheme[0].scheme==record")
            canvas?.drawCircle((centerX+mItemHeight/4).toFloat(), centerY.toFloat(), (22).toFloat(), paintEventDates)
        }

        if (schemes[0].scheme=="range"){  // 有scheme
            val dayOfWeek=CalendarUtil.getWeekFromCalendar(calendar)
            if (dayOfWeek==1){
                // if this day is monday(the most left position), draw circle and half rect
                canvas?.drawCircle((centerX+mItemHeight/4).toFloat(), centerY.toFloat(), (mItemHeight / 2).toFloat(), paintRangeDates)
                canvas?.drawRect((centerX+mItemHeight/4).toFloat(),y.toFloat(),(x+mItemWidth).toFloat(),(y+mItemHeight).toFloat(),paintRangeDates)
            }else if (dayOfWeek==0){
                // if this day is sunday(the most right position), draw circle and half rect
                canvas?.drawCircle((centerX+mItemHeight/4).toFloat(), centerY.toFloat(), (mItemHeight / 2).toFloat(), paintRangeDates)
                canvas?.drawRect(x.toFloat(),y.toFloat(),(centerX+mItemHeight/4).toFloat(),(y+mItemHeight).toFloat(),paintRangeDates)

            } else{
                // else these background is drew in rectangle
                canvas?.drawRect(x.toFloat(), y.toFloat(), (x + mItemWidth).toFloat(), (y + mItemHeight).toFloat(), paintRangeDates)
            }
            if (schemes.size>1) {
                Log.d("calendarmonth","scheme size >1, ${schemes.get(1).scheme}")
                if (schemes[1].scheme == "record") {
                    canvas?.drawCircle((centerX + mItemHeight / 4).toFloat(), centerY.toFloat(), (22).toFloat(), paintEventDates)
                }
            }
        }

         */

        Log.d("howabout","current month, $calendar")
        /*
        if (calendar_bp.isCurrentMonth){
            Log.d("howabout","current month, $calendar_bp")
        }else{
            Log.d("howabout","not in current month, $calendar_bp")
        }

         */


        /*
        val schemes=calendar_bp.schemes
        if (schemes?.get(1)?.scheme=="record"){

            canvas?.drawCircle(centerX.toFloat(), centerY.toFloat(), (mItemWidth / 2).toFloat(), paintEventDates)
        }

         */
    }

    override fun onDrawText(canvas: Canvas?, calendar: Calendar?, x: Int, y: Int, hasScheme: Boolean, isSelected: Boolean) {



        Log.d("onSelect","in onDrawText part ${calendar.toString()}, $isSelected, $x, $y")
        //Log.d("ondrawtext","${mItemWidth}, $mItemHeight")
        //                          94,         56
        val baselineY=mTextBaseLine+y
        val centerX=(x+mItemWidth/2).toFloat()
        val centerY=(y+mItemHeight/1.8).toFloat()


        val day=calendar?.day

        var wordX=0f
        if (day!=null){
            if (day<10){  // 如果day只有一位數, 要把數字再往右一點, 目前粗估5px
                wordX=5f
            }
        }


        if (calendar!!.isCurrentMonth){ // 是指定顯示該月的範圍

            if(calendar.isCurrentDay){ // 是今天?

                canvas?.drawCircle(centerX + (mItemHeight / 4.7).toFloat(), centerY, (22).toFloat(), paintTodayCircle)
                canvas?.drawText(
                        day.toString(),
                        centerX+wordX,
                        baselineY,
                        paintToday)
            }else{ // 是本月的其他天

                calendarCompare.set(calendar.year, calendar.month - 1, calendar.day)

                if (calendarCompare.before(calendarNow)){ //是本月的今天之前
                    //Log.d("calendarMonth", "before, ${calendar_bp.day}")
                    canvas?.drawText(
                            calendar.day.toString(),
                            centerX+wordX,
                            baselineY,
                            currentMonthPassedDates)

                }else if (calendarCompare.after(calendarNow)){ //是本月的今天之後
                    //Log.d("calendarMonth", "after, ${calendar_bp.day}")
                    canvas?.drawText(
                            calendar.day.toString(),
                            centerX+wordX,
                            baselineY,
                            currentMonthComingDates)
                }
            }
        }else{// 是指定顯示該月的 上個月 or 下個月的範圍

            canvas?.drawText(
                    calendar.day.toString(),
                    centerX+wordX,
                    baselineY,
                    nextMonthDates)

            /*
            if (calendar.day<15){// 下個月的範圍
                canvas?.drawText(
                        calendar.day.toString(),
                        centerX+wordX,
                        baselineY,
                        nextMonthDates)

            }

             */
            //Log.d("hohoho","fuck ${calendar_bp.schemes.size}")
        }

        if (isSelected){ //redraw被onDrawSelected蓋掉的數字


            when(CalendarUtil.duration){

                CalendarUtil.week->{
                    Log.d("testselect","week")
                    var xCount=(x/mItemWidth)-1
                    if (xCount<0){
                        return
                    }

                }

                CalendarUtil.month->{
                    Log.d("testselect","month")
                    var xCount=(x/mItemWidth)-1
                    var yCount=y/mItemHeight

                    if (xCount<0){
                        xCount=6
                        yCount=yCount-1
                    }
                }

                else->{
                    Log.d("testselect","else")
                }
            }

        }
    }
}