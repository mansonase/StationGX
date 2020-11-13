package com.example.stationgx.ui.custom.calendar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
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

    init {

        lastMonthDates.color= context.getColor(android.R.color.transparent)

        nextMonthDates.color=context.getColor(R.color.frame_grey)
        nextMonthDates.textSize=24f

        currentMonthPassedDates.color=context.getColor(R.color.word_black)
        currentMonthPassedDates.textSize=24f

        currentMonthComingDates.color=context.getColor(R.color.icon_grey)
        currentMonthComingDates.textSize=24f

        paintToday.color=context.getColor(R.color.main_white)
        paintTodayCircle.color=context.getColor(R.color.word_blue)
        paintTodayCircle.style=Paint.Style.FILL
        paintEventDates.color=context.getColor(R.color.border_blue)
        paintEventDates.style=Paint.Style.STROKE
        paintEventDates.strokeWidth=2f
        paintRangeDates.color=context.getColor(R.color.background_pink)
    }

    override fun onDrawSelected(canvas: Canvas?, calendar: Calendar?, x: Int, y: Int, hasScheme: Boolean): Boolean {

        return false
    }

    override fun onDrawScheme(canvas: Canvas?, calendar: Calendar?, x: Int, y: Int) {
        val centerX=x+mItemWidth/2
        val centerY=y+mItemHeight/2


        val schemes=calendar?.schemes

        if (schemes?.get(0)?.scheme == "single"){
            canvas?.drawCircle(centerX.toFloat(),centerY.toFloat(),(mItemWidth/2).toFloat(),paintTodayCircle)
            canvas?.drawCircle(centerX.toFloat(),centerY.toFloat(),(mItemWidth/2).toFloat(),paintEventDates)
        }else if (schemes?.get(0)?.scheme=="range"){

            val dayOfWeek=CalendarUtil.getWeekFromCalendar(calendar)
            if (dayOfWeek==1.or(0)){
                canvas?.drawCircle(centerX.toFloat(),centerY.toFloat(),(mItemWidth/2).toFloat(),paintRangeDates)
            }else{
                canvas?.drawRect(x.toFloat(),y.toFloat(),x+mItemHeight.toFloat(),(y+mItemHeight).toFloat(),paintRangeDates)
            }

        }

    }

    override fun onDrawText(canvas: Canvas?, calendar: Calendar?, x: Int, y: Int, hasScheme: Boolean, isSelected: Boolean) {

        val baselineY=mTextBaseLine+y
        val centerX=x+mItemWidth/2

        if (hasScheme){
            
        }else{

        }
    }


}