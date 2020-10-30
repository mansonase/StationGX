package com.example.stationgx.ui.mpchart

import android.graphics.Color
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.stationgx.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.components.MarkerImage
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet

class BloodPressureBarChart(private val barChart: BarChart,private val duration:String):ChartContract<BarChart,BarData> {

    val todayInterval= arrayOf("0:00","06:00","12:00","18:00","24:00")
    val weekInterval= arrayOf("Mon","Tue","Wed","Thu","Fri","Sat","Sun","")
    //val weekInterval= arrayOf("Mon","Tue","Wed","Thu")
    val monthInterval= arrayOf("7th","14th","21th","28th")


    override fun getChart(): BarChart {
        //TODO("Not yet implemented")
        barChart.setBackgroundColor(Color.WHITE)
        barChart.setDrawGridBackground(false)
        barChart.setDrawBarShadow(false)
        barChart.isHighlightFullBarEnabled=false
        barChart.setDrawBorders(false)
        barChart.description.isEnabled=false
        barChart.background=ContextCompat.getDrawable(barChart.context,R.drawable.blood_pressure_mp_background)
        barChart.renderer=BloodPressureRenderer(barChart,barChart.animator,barChart.viewPortHandler,duration)



        barChart.axisLeft.setDrawGridLines(false)
        barChart.axisLeft.setDrawAxisLine(false)
        barChart.axisLeft.axisMinimum=50f
        barChart.axisRight.setDrawGridLines(false)
        barChart.axisRight.setDrawAxisLine(false)
        barChart.axisRight.setDrawLabels(false)
        barChart.axisRight.axisMinimum=50f
        barChart.xAxis.setDrawGridLines(false)
        barChart.xAxis.setDrawAxisLine(false)
        barChart.xAxis.setDrawLabels(true)
        barChart.xAxis.position=XAxis.XAxisPosition.BOTTOM
        barChart.xAxis.granularity=0.85f

        barChart.xAxis.setLabelCount(8,true)
        barChart.xAxis.axisMinimum=0f
        barChart.xAxis.axisMaximum=168f





        var interval:Array<String>?=null
        when(duration){
            "today"-> interval=todayInterval
            "week"->  interval=weekInterval
            "month"-> interval=monthInterval
        }

        //barChart.xAxis.valueFormatter=BloodPressureXFormatter(interval!!)
        barChart.xAxis.valueFormatter=BloodPressureXAxisFormatter(interval!!)

        barChart.setTouchEnabled(false)
        barChart.setDrawBarShadow(false)


        val legend=barChart.legend
        legend.isEnabled=false

        legend.setDrawInside(false)// 有差 所以設定要小心
        legend.verticalAlignment=Legend.LegendVerticalAlignment.BOTTOM
        legend.orientation=Legend.LegendOrientation.HORIZONTAL


        return barChart
    }

    override fun getData(): BarData {
        //TODO("Not yet implemented")
        return getList(duration)
    }

    private fun getList(duration: String):BarData{

        val context=barChart.context

        val bpList=BloodPressureSource(duration)
        val barDataSet=BarDataSet(bpList.getBarEntry(),"")


        barDataSet.color=Color.TRANSPARENT
        barDataSet.setDrawValues(false)


        return BarData(barDataSet)
    }

}