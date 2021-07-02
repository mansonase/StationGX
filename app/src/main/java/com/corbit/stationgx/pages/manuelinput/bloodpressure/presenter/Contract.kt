package com.corbit.stationgx.pages.manuelinput.bloodpressure.presenter


interface Contract {
    interface IView<T,S>{
        fun updateChart(arrayList:ArrayList<S>)
        fun updateTable(list: ArrayList<T>)
    }
    interface IPresenter{
        fun saveData(index:Long,systolic:Int,diastolic:Int,time:Int,note:String)
        fun getDayData(startTime: Int)
        fun getWeekData(startTime: Int)
        fun getMonthData(startTime: Int)
    }
}