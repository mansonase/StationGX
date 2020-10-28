package com.example.stationgx.ui.mpchart

import android.graphics.Color
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.stationgx.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.components.MarkerImage
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet

class BloodPressureBarChart(private val barChart: BarChart,private val duration:String):ChartContract<BarChart,BarData> {


    override fun getChart(): BarChart {
        //TODO("Not yet implemented")
        barChart.setBackgroundColor(Color.WHITE)
        barChart.setDrawGridBackground(false)
        barChart.setDrawBarShadow(false)
        barChart.isHighlightFullBarEnabled=false
        barChart.setDrawBorders(false)
        barChart.description.isEnabled=false
        barChart.background=ContextCompat.getDrawable(barChart.context,R.drawable.blood_pressure_mp_background)
        barChart.renderer=BloodPressureRenderer(barChart,barChart.animator,barChart.viewPortHandler)


        barChart.axisLeft.setDrawGridLines(false)
        barChart.axisLeft.setDrawAxisLine(false)
        barChart.axisLeft.axisMinimum=0f
        barChart.axisRight.setDrawGridLines(false)
        barChart.axisRight.setDrawAxisLine(false)
        barChart.axisRight.setDrawLabels(false)
        barChart.axisRight.axisMinimum=0f
        barChart.xAxis.setDrawGridLines(false)
        barChart.xAxis.setDrawAxisLine(false)
        barChart.xAxis.setDrawLabels(false)

        barChart.setTouchEnabled(false)
        barChart.setDrawBarShadow(false)


        val legend=barChart.legend
        legend.isEnabled=true

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