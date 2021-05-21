package com.corbit.stationgx.apis.dateapi

import android.content.Intent
import android.content.IntentFilter
import javax.inject.Inject

open class TimeIntent@Inject constructor() {

    fun intentFilter():IntentFilter{
        val filter=IntentFilter()
        filter.addAction(Intent.ACTION_TIME_CHANGED)
        filter.addAction(Intent.ACTION_TIMEZONE_CHANGED)
        filter.addAction(Intent.ACTION_TIME_TICK)
        filter.addAction(Intent.ACTION_DATE_CHANGED)
        return filter
    }
}