package com.example.stationgx.ui.custom.calendar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.Log
import androidx.core.content.res.ResourcesCompat
import com.example.stationgx.R
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
        val font=ResourcesCompat.getFont(context,R.font.roboto_bold)

        lastMonthDates.color= resources.getColor(android.R.color.transparent,null)

        nextMonthDates.color=resources.getColor(R.color.frame_grey,null)
        nextMonthDates.textSize=24f
        nextMonthDates.typeface=font

        currentMonthPassedDates.color=resources.getColor(R.color.word_black,null)
        currentMonthPassedDates.textSize=24f
        currentMonthPassedDates.typeface = font


        currentMonthComingDates.color=resources.getColor(R.color.icon_grey,null)
        currentMonthComingDates.textSize=24f
        currentMonthComingDates.typeface=font

        paintToday.color=resources.getColor(R.color.main_white,null)
        paintToday.textSize=24f
        paintToday.typeface=font

        paintTodayCircle.color=resources.getColor(R.color.word_blue,null)
        paintTodayCircle.style=Paint.Style.FILL

        paintEventDates.color=resources.getColor(R.color.border_blue,null)
        paintEventDates.style=Paint.Style.STROKE
        paintEventDates.strokeWidth=2f


        Log.d("init","testingggg")
        paintRangeDates.color=resources.getColor(R.color.background_pink,null)
        paintRangeDates.style=Paint.Style.FILL
    }

    override fun onDrawSelected(canvas: Canvas?, calendar: Calendar?, x: Int, y: Int, hasScheme: Boolean): Boolean {

        return false
    }

    override fun onDrawScheme(canvas: Canvas?, calendar: Calendar?, x: Int, y: Int) {
        val centerX=x+mItemWidth/2
        val centerY=y+mItemHeight/2

        if (calendar!=null) {
            Log.d("ondrawscheme", "${calendar.day}")
        }else{
            Log.d("ondrawscheme", "null , $x")
        }

        if (calendar?.scheme=="today"){
            // draw today's circle and border
            canvas?.drawCircle(centerX.toFloat(),centerY.toFloat(),(mItemWidth/2).toFloat(),paintTodayCircle)
            canvas?.drawCircle(centerX.toFloat(),centerY.toFloat(),(mItemWidth/2).toFloat(),paintEventDates)
        }else if (calendar?.scheme=="range"||calendar?.scheme=="both"){

            // draw this month or week's background
            val dayOfWeek=CalendarUtil.getWeekFromCalendar(calendar)
            if (dayOfWeek==1.or(0)){
                // if this day is monday(the most left position) or sunday(the most right position), draw circle
                canvas?.drawCircle(centerX.toFloat(),centerY.toFloat(),(mItemWidth/2).toFloat(),paintRangeDates)
            }else{
                // else these background is drew in rectangle
                canvas?.drawRect(x.toFloat(),y.toFloat(),x+mItemHeight.toFloat(),(y+mItemHeight).toFloat(),paintRangeDates)
            }

            if (calendar.scheme =="both"){

                canvas?.drawCircle(centerX.toFloat(),centerY.toFloat(),(mItemWidth/2).toFloat(),paintTodayCircle)
                canvas?.drawCircle(centerX.toFloat(),centerY.toFloat(),(mItemWidth/2).toFloat(),paintEventDates)
            }

        }

        val schemes=calendar?.schemes
        if (schemes?.get(0)?.scheme=="record"){

            canvas?.drawCircle(centerX.toFloat(),centerY.toFloat(),(mItemWidth/2).toFloat(),paintEventDates)
        }
    }

    override fun onDrawText(canvas: Canvas?, calendar: Calendar?, x: Int, y: Int, hasScheme: Boolean, isSelected: Boolean) {

        //Log.d("ondrawtext","${mItemWidth}, $mItemHeight")
        //                          94,         56
        val baselineY=mTextBaseLine+y
        val centerX=(x+mItemWidth/2).toFloat()
        val centerY=(y+mItemHeight/2).toFloat()

        val day=calendar?.day

        if (calendar!!.isCurrentMonth){ // 是指定顯示該月的範圍

            if(calendar.isCurrentDay){ // 是今天?

                canvas?.drawCircle(centerX+(mItemHeight/4),centerY,(22).toFloat(),paintTodayCircle)
                canvas?.drawText(
                        day.toString(),
                        centerX,
                        baselineY,
                        paintToday)
            }else{ // 是本月的其他天

                calendarCompare.set(calendar.year,calendar.month-1,calendar.day)

                if (calendarCompare.before(calendarNow)){ //是本月的今天之前
                    Log.d("calendarMonth","before, ${calendar.day}")
                    canvas?.drawText(
                            calendar.day.toString(),
                            centerX,
                            baselineY,
                            currentMonthPassedDates)

                }else if (calendarCompare.after(calendarNow)){ //是本月的今天之後
                    Log.d("calendarMonth","after, ${calendar.day}")
                    canvas?.drawText(
                            calendar.day.toString(),
                            centerX,
                            baselineY,
                            currentMonthComingDates)
                }
            }
        }else{// 是指定顯示該月的 上個月 or 下個月的範圍

            if (calendar.day<15){// 下個月的範圍
                canvas?.drawText(
                        calendar.day.toString(),
                        centerX,
                        baselineY,
                        nextMonthDates)
            }
        }


        if (calendar.hasScheme()){
            Log.d("ondrawtext","has scheme")
        }else{
            Log.d("ondrawtext","has No scheme")
        }
        val schemes=calendar.schemes

        if (schemes!=null){
            Log.d("bpcalendarff","scheme size ${schemes.size}, ${calendar.day}")
            if (schemes[0]!=null){
                if (schemes[0].scheme=="record"){

                    canvas?.drawCircle(centerX,centerY,(mItemWidth/2).toFloat(),paintEventDates)
                }
            }
        }else{
            Log.d("bpcalendarff","${calendar.day}, ${calendar.scheme}")
        }

        /*
        Log.d("bpcalendarff","scheme size ${schemes.size}, ${calendar.day}")
        if (schemes[0]!=null){
            if (schemes[0].scheme=="record"){

                canvas?.drawCircle(centerX,centerY,(mItemWidth/2).toFloat(),paintEventDates)
            }
        }

         */



    }
}