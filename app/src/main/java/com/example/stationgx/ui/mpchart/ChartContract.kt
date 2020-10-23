package com.example.stationgx.ui.mpchart

interface ChartContract<C,D> {

    fun getChart():C
    fun getData() :D
}