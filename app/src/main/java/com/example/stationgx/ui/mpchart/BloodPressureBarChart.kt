package com.example.stationgx.ui.mpchart

import android.graphics.Color
import androidx.core.content.res.ResourcesCompat
import com.example.stationgx.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.*
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet

class BloodPressureBarChart(private val barChart: BloodPressureBackgroundBarChart, private val duration:String):ChartContract<BloodPressureBackgroundBarChart,BarData> {

    val todayInterval= arrayOf("0:00","06:00","12:00","18:00","24:00")
    val weekInterval= arrayOf("Mon","Tue","Wed","Thu","Fri","Sat","Sun","")
    val monthInterval= arrayOf("","7th","14th","21th","28th","")


    override fun getChart(): BloodPressureBackgroundBarChart {
        //TODO("Not yet implemented")
        barChart.clear()
        barChart.setBackgroundColor(Color.WHITE)
        barChart.setDrawGridBackground(false)
        barChart.setDrawBarShadow(false)
        barChart.isHighlightFullBarEnabled=false
        barChart.setDrawBorders(false)
        barChart.description.isEnabled=false
        barChart.renderer=BloodPressureRenderer(barChart,barChart.animator,barChart.viewPortHandler,duration)



        barChart.axisLeft.setDrawGridLines(false)
        barChart.axisLeft.setDrawAxisLine(false)
        barChart.axisLeft.axisMinimum=60f
        barChart.axisLeft.axisMaximum=180f
        barChart.axisLeft.setLabelCount(4,true)
        barChart.axisLeft.textSize=24f
        barChart.axisLeft.textColor=R.color.label_black
        barChart.axisLeft.typeface= ResourcesCompat.getFont(barChart.context,R.font.roboto_bold)


        setLimitLine(barChart)

        barChart.axisRight.setDrawGridLines(false)
        barChart.axisRight.setDrawAxisLine(false)
        barChart.axisRight.setDrawLabels(false)
        barChart.axisRight.axisMinimum=60f

        barChart.xAxis.setDrawGridLines(false)
        barChart.xAxis.setDrawAxisLine(false)
        barChart.xAxis.setDrawLabels(true)
        barChart.xAxis.position=XAxis.XAxisPosition.BOTTOM
        //barChart.xAxis.granularity=0.85f

        //barChart.xAxis.setLabelCount(8,true)
        barChart.xAxis.axisMinimum=0f
        //barChart.xAxis.axisMaximum=168f
        barChart.xAxis.textSize=24f
        barChart.xAxis.textColor=R.color.label_black
        barChart.extraBottomOffset=10f
        barChart.xAxis.setAvoidFirstLastClipping(true)


        var interval:Array<String>?=null
        when(duration){
            "today"-> {
                interval=todayInterval
                barChart.xAxis.setLabelCount(5,true)
                barChart.xAxis.axisMaximum=24f
            }
            "week"-> {
                interval = weekInterval
                barChart.xAxis.setLabelCount(8,true)
                barChart.xAxis.axisMaximum=168f
            }
            "month"-> {
                interval=monthInterval
                barChart.xAxis.setLabelCount(6,true)
                barChart.xAxis.axisMaximum=35f
            }
        }

        //barChart.xAxis.valueFormatter=BloodPressureXFormatter(interval!!)
        //todo 設X軸會在切換today/week/month時crash (不好處理, 可能先不要放顯示X軸的功能)
        //barChart.xAxis.valueFormatter=BloodPressureXAxisFormatter(interval!!)

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
        val range= barDataSet.getEntryForIndex(0).yVals


        barDataSet.color=Color.TRANSPARENT
        barDataSet.setDrawValues(false)


        return BarData(barDataSet)
    }

    fun setLimitLine(barChart: BarChart):BarChart{

        val limitline180=LimitLine(180f,"")
        val limitline140=LimitLine(140f,"")
        val limitline100=LimitLine(100f,"")
        val limitline60 =LimitLine(60f,"")
        limitline180.lineColor=Color.parseColor("#AAABAD")
        limitline140.lineColor=Color.parseColor("#AAABAD")
        limitline100.lineColor=Color.parseColor("#AAABAD")
        limitline60.lineColor= Color.parseColor("#AAABAD")
        limitline180.lineWidth=1f
        limitline140.lineWidth=1f
        limitline100.lineWidth=1f
        limitline60.lineWidth=1f

        limitline180.enableDashedLine(10f,2f,0f)
        limitline140.enableDashedLine(10f,2f,0f)
        limitline100.enableDashedLine(10f,2f,0f)
        limitline60.enableDashedLine(10f,2f,0f)

        barChart.axisLeft.removeAllLimitLines()
        barChart.axisLeft.addLimitLine(limitline180)
        barChart.axisLeft.addLimitLine(limitline140)
        barChart.axisLeft.addLimitLine(limitline100)
        barChart.axisLeft.addLimitLine(limitline60)
        barChart.axisLeft.setDrawLimitLinesBehindData(true)


        return barChart
    }

}