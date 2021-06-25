package com.corbit.stationgx.ui.mpchart.weight

import android.graphics.Color
import androidx.core.content.res.ResourcesCompat
import com.corbit.stationgx.R
import com.corbit.stationgx.ui.mpchart.ChartContract
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.*
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class WeightLineChart(private val lineChart: WeightBackgroundLineChart, private val duration:String): ChartContract<WeightBackgroundLineChart, LineData> {

    val todayInterval= arrayOf("0:00","06:00","12:00","18:00","24:00")
    val weekInterval= arrayOf("Mon","Tue","Wed","Thu","Fri","Sat","Sun","")
    val monthInterval= arrayOf("","7th","14th","21th","28th","")


    override fun getChart(): WeightBackgroundLineChart {
        //TODO("Not yet implemented")
        lineChart.clear()
        lineChart.setBackgroundColor(Color.WHITE)
        lineChart.setDrawGridBackground(false)
        //lineChart.setDrawBarShadow(false)
        //lineChart.isHighlightFullBarEnabled=false
        lineChart.setDrawBorders(false)
        lineChart.description.isEnabled=false
        //lineChart.renderer=BloodPressureRenderer(lineChart,lineChart.animator,lineChart.viewPortHandler,duration)



        lineChart.axisLeft.setDrawGridLines(false)
        lineChart.axisLeft.setDrawAxisLine(false)
        lineChart.axisLeft.axisMinimum=55f
        lineChart.axisLeft.axisMaximum=70f
        lineChart.axisLeft.setLabelCount(4,true)
        lineChart.axisLeft.textSize=24f
        lineChart.axisLeft.textColor=R.color.label_black
        lineChart.axisLeft.typeface= ResourcesCompat.getFont(lineChart.context,R.font.roboto_bold)


        setLimitLine(lineChart)

        lineChart.axisRight.setDrawGridLines(false)
        lineChart.axisRight.setDrawAxisLine(false)
        lineChart.axisRight.setDrawLabels(false)
        lineChart.axisRight.axisMinimum=60f

        lineChart.xAxis.setDrawGridLines(false)
        lineChart.xAxis.setDrawAxisLine(false)
        lineChart.xAxis.setDrawLabels(true)
        lineChart.xAxis.position=XAxis.XAxisPosition.BOTTOM
        //barChart.xAxis.granularity=0.85f

        //barChart.xAxis.setLabelCount(8,true)
        lineChart.xAxis.axisMinimum=0f
        //barChart.xAxis.axisMaximum=168f
        lineChart.xAxis.textSize=24f
        lineChart.xAxis.textColor=R.color.label_black
        lineChart.extraBottomOffset=10f
        lineChart.xAxis.setAvoidFirstLastClipping(true)


        var interval:Array<String>?=null
        when(duration){
            "today"-> {
                interval=todayInterval
                lineChart.xAxis.setLabelCount(5,true)
                lineChart.xAxis.axisMaximum=24f
            }
            "week"-> {
                interval = weekInterval
                lineChart.xAxis.setLabelCount(8,true)
                lineChart.xAxis.axisMaximum=168f
            }
            "month"-> {
                interval=monthInterval
                lineChart.xAxis.setLabelCount(6,true)
                lineChart.xAxis.axisMaximum=35f
            }
        }

        //barChart.xAxis.valueFormatter=BloodPressureXFormatter(interval!!)
        //todo 設X軸會在切換today/week/month時crash (不好處理, 可能先不要放顯示X軸的功能)
        //barChart.xAxis.valueFormatter=BloodPressureXAxisFormatter(interval!!)

        lineChart.setTouchEnabled(false)
        //lineChart.setDrawBarShadow(false)

        val legend=lineChart.legend
        legend.isEnabled=false

        legend.setDrawInside(false)// 有差 所以設定要小心
        legend.verticalAlignment=Legend.LegendVerticalAlignment.BOTTOM
        legend.orientation=Legend.LegendOrientation.HORIZONTAL


        return lineChart
    }

    override fun getData(): LineData {
        //TODO("Not yet implemented")
        return getList(duration)
    }

    private fun getList(duration: String):LineData{

        val context=lineChart.context

        val weightList=WeightSource(duration)
        val lineDataSet= LineDataSet(weightList.getLineEntry(),"")
        val weight= lineDataSet.getEntryForIndex(0).y


        lineDataSet.color=Color.TRANSPARENT
        lineDataSet.setDrawValues(false)


        return LineData(lineDataSet)
    }

    fun setLimitLine(lineChart1: LineChart):LineChart{

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

        lineChart1.axisLeft.removeAllLimitLines()
        lineChart1.axisLeft.addLimitLine(limitline180)
        lineChart1.axisLeft.addLimitLine(limitline140)
        lineChart1.axisLeft.addLimitLine(limitline100)
        lineChart1.axisLeft.addLimitLine(limitline60)
        lineChart1.axisLeft.setDrawLimitLinesBehindData(true)


        return lineChart1
    }

}