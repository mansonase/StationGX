package com.corbit.stationgx.ui.mpchart

interface ChartContract<C,D> {

    fun getChart():C
    fun getData() :D
}