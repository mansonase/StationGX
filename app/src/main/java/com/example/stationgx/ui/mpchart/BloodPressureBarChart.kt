package com.example.stationgx.ui.mpchart

import android.graphics.Color
import androidx.core.content.ContextCompat
import com.example.stationgx.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
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
        barChart.background=ContextCompat.getDrawable(barChart.context,R.drawable.blood_pressure_mp_background)

        barChart.axisLeft.setDrawGridLines(false)
        barChart.axisLeft.setDrawAxisLine(false)
        barChart.axisLeft.axisMinimum=50f
        barChart.axisRight.setDrawGridLines(false)
        barChart.axisRight.setDrawAxisLine(false)
        barChart.axisRight.setDrawLabels(false)
        barChart.axisRight.axisMinimum=50f
        barChart.xAxis.setDrawGridLines(false)
        barChart.xAxis.setDrawAxisLine(false)
        barChart.xAxis.setDrawLabels(false)

        barChart.legend.isEnabled=false
        barChart.description=null

        val legend=barChart.legend
        legend.setDrawInside(false)
        legend.verticalAlignment=Legend.LegendVerticalAlignment.BOTTOM
        legend.orientation=Legend.LegendOrientation.HORIZONTAL
        legend.textSize=24f

        return barChart
    }

    override fun getData(): BarData {
        //TODO("Not yet implemented")
        return getList(duration)
    }

    private fun getList(duration: String):BarData{
        val bpList=BloodPressureSource(duration)
        val barDataSet=BarDataSet(bpList.getBarEntry(),"test")

        val context=barChart.context
        val colors= ArrayList<Int>()

        colors.add(context.getColor(android.R.color.transparent))
        colors.add(context.getColor(R.color.background_blue))
        barDataSet.colors = colors
        barDataSet.setDrawValues(false)

        return BarData(barDataSet)
    }

}