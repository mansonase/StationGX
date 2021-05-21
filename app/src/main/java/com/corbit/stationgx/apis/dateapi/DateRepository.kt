package com.corbit.stationgx.apis.dateapi

import java.util.*

interface DateRepository {

   fun onDateChanged(calendar: Calendar)

}