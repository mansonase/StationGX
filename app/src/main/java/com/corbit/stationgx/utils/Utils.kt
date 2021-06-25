package com.corbit.stationgx.utils

import java.util.*

class Utils {


    companion object{
       fun createKeyIndex(): Long {
            return Calendar.getInstance().timeInMillis.toLong()
        }
    }
}