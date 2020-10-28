package com.example.stationgx.ui.mpchart

import android.graphics.*
import com.github.mikephil.charting.animation.ChartAnimator
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.renderer.BarChartRenderer
import com.github.mikephil.charting.utils.Utils
import com.github.mikephil.charting.utils.ViewPortHandler
import kotlin.math.ceil

class BloodPressureRenderer(chart: BarDataProvider?, animator: ChartAnimator?, viewPortHandler: ViewPortHandler?) : BarChartRenderer(chart, animator, viewPortHandler) {

    private val mBarShadowRectBuffer=RectF()




    override fun drawDataSet(c: Canvas?, dataSet: IBarDataSet?, index: Int) {
        super.drawDataSet(c, dataSet, index)

        val trans=mChart.getTransformer(dataSet?.axisDependency)
        mBarBorderPaint.color = dataSet!!.barBorderColor
        mBarBorderPaint.strokeWidth=Utils.convertDpToPixel(dataSet.barBorderWidth)


        val drawBorder= dataSet.barBorderWidth >0f
        var phaseX=mAnimator.phaseX
        var phaseY=mAnimator.phaseY


        // draw the bar shadow before the values
        if (mChart.isDrawBarShadowEnabled){
            mShadowPaint.color=dataSet.barShadowColor
            val barData=mChart.barData

            val barWidth=barData.barWidth
            val barWidthHalf=barWidth/2.0f

            var x:Float?

            for (i in 0 until ((ceil(dataSet.entryCount.toFloat()) * phaseX).coerceAtMost(dataSet.entryCount.toFloat()).toInt())){

                val e=dataSet.getEntryForIndex(i)
                x=e.x

                mBarShadowRectBuffer.left=  x- barWidthHalf
                mBarShadowRectBuffer.right= x+ barWidthHalf

                trans.rectValueToPixel(mBarShadowRectBuffer)

                if (!mViewPortHandler.isInBoundsLeft(mBarShadowRectBuffer.right)){
                    continue
                }
                if (!mViewPortHandler.isInBoundsRight(mBarShadowRectBuffer.left)){
                    break
                }

                mBarShadowRectBuffer.top=mViewPortHandler.contentTop()
                mBarShadowRectBuffer.bottom=mViewPortHandler.contentBottom()

                c?.drawRect(mBarShadowRectBuffer,mShadowPaint)
            }
        }

        // initialize the buffer
        val buffer=mBarBuffers[index]
        buffer.setPhases(phaseX,phaseY)
        buffer.setDataSet(index)
        buffer.setInverted(mChart.isInverted(dataSet.axisDependency))
        buffer.setBarWidth(mChart.barData.barWidth)


        buffer.feed(dataSet)

        trans.pointValuesToPixel(buffer.buffer)


        val isSingleColor=dataSet.colors.size==1
        if (isSingleColor){
            mRenderPaint.color=dataSet.color
        }


        mRenderPaint.color = Color.rgb(0xE1,0x5F,0x64)

        for (j in 0 until buffer.size() step 4){

            if (!mViewPortHandler.isInBoundsLeft(buffer.buffer[j+2])){
                continue
            }
            if (!mViewPortHandler.isInBoundsRight(buffer.buffer[j])){
                break
            }
            if (!isSingleColor){
                //  set the color for the currently drawn value
                // If the index is out of bounds, reuse colors
                mRenderPaint.color=dataSet.getColor(j/4)
            }

            val left=buffer.buffer[j]
            val top=buffer.buffer[j+1]
            val right=buffer.buffer[j+2]
            val bottom=buffer.buffer[j+3]
            val radius=(right-left)/2


            c?.drawRoundRect(RectF(left, top, right, bottom),radius,radius,mRenderPaint)
        }
    }
}
