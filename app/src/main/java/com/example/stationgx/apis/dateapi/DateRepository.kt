package com.example.stationgx.apis.dateapi

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import java.util.*

interface DateRepository {

   fun onDateChanged(calendar: Calendar)

}