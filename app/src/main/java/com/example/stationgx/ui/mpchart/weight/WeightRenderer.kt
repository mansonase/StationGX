package com.example.stationgx.ui.mpchart.weight

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Color.blue
import android.graphics.Paint
import com.example.stationgx.R
import com.github.mikephil.charting.animation.ChartAnimator
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.renderer.LineChartRenderer
import com.github.mikephil.charting.utils.ViewPortHandler

class WeightRenderer(chart: LineDataProvider?, animator: ChartAnimator?, viewPortHandler: ViewPortHandler?,private val duration:String) : LineChartRenderer(chart, animator, viewPortHandler) {


    var paintCircle: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var radius=0

    init {
        paintCircle.style=Paint.Style.FILL
        paintCircle.color= Color.rgb(0x63,0xB8,0xE3)

        radius= when(duration){
            "today"->12
            "week"->8
            "month"->4
            else -> 0
        }
    }
    override fun drawDataSet(c: Canvas?, dataSet: ILineDataSet?) {
        super.drawDataSet(c, dataSet)



    }



    override fun drawValues(c: Canvas?) {
        super.drawValues(c)

        val dataset=mChart.lineData.getDataSetByIndex(0)
        val trans=mChart.getTransformer(dataset.axisDependency)


    }
}