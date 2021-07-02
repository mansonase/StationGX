package com.corbit.stationgx.ui.custom.calendar

import android.content.Context
import android.graphics.Canvas
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.MonthView

class CalendarMonthRevise1(context:Context):MonthView(context) {
    override fun onDrawSelected(canvas: Canvas?, calendar: Calendar?, x: Int, y: Int, hasScheme: Boolean): Boolean {
        return true
    }

    override fun onDrawScheme(canvas: Canvas?, calendar: Calendar?, x: Int, y: Int) {

    }

    override fun onDrawText(canvas: Canvas?, calendar: Calendar?, x: Int, y: Int, hasScheme: Boolean, isSelected: Boolean) {

    }
}