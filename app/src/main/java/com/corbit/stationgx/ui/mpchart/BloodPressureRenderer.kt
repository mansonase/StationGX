package com.corbit.stationgx.ui.mpchart

import android.graphics.*
import android.util.Log
import com.github.mikephil.charting.animation.ChartAnimator
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.renderer.BarChartRenderer
import com.github.mikephil.charting.utils.Utils
import com.github.mikephil.charting.utils.ViewPortHandler

class BloodPressureRenderer(chart: BarDataProvider?, animator: ChartAnimator?, viewPortHandler: ViewPortHandler?, private val duration: String) : BarChartRenderer(chart, animator, viewPortHandler) {

    private val mBarShadowRectBuffer=RectF()


    override fun drawDataSet(c: Canvas?, dataSet: IBarDataSet?, index: Int) {
        super.drawDataSet(c, dataSet, index)

        val trans=mChart.getTransformer(dataSet?.axisDependency)
        mBarBorderPaint.color = dataSet!!.barBorderColor
        mBarBorderPaint.strokeWidth=Utils.convertDpToPixel(dataSet.barBorderWidth)


        var phaseX=mAnimator.phaseX
        var phaseY=mAnimator.phaseY


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

        val mRenderGrey=Paint(Paint.ANTI_ALIAS_FLAG)
        mRenderGrey.color=Color.parseColor("#B2F5F5F5")
        //mRenderGrey.color=Color.parseColor("#050505")
        mRenderGrey.style=Paint.Style.FILL
        val mRenderBlue=Paint(Paint.ANTI_ALIAS_FLAG)
        mRenderBlue.color=Color.parseColor("#960288D1")
        mRenderBlue.style=Paint.Style.FILL
        val mRenderRed=Paint(Paint.ANTI_ALIAS_FLAG)
        mRenderRed.color=Color.parseColor("#96E15F64")
        mRenderRed.style=Paint.Style.FILL

        Log.d("testinging","buffer size is ${buffer.size()}")

        for (j in 0 until buffer.size() step 8){

            if (!mViewPortHandler.isInBoundsLeft(buffer.buffer[j+2])){
                continue
            }
            if (!mViewPortHandler.isInBoundsRight(buffer.buffer[j])){
                break
            }


            // UI assign it as 8px  (16*16)
            val radius=8f

            val lowerLeft=buffer.buffer[j]
            val lowerTop=buffer.buffer[j+1]
            //val right=buffer.buffer[j+2]
            val lowerRight=lowerLeft+radius*2
            val lowerBottom=buffer.buffer[j+3]
            //val radius=(right-left)/2

            val upperLeft=buffer.buffer[j+4]
            val upperTop=buffer.buffer[j+5]
            val upperRight=upperLeft+radius*2
            val upperBottom=buffer.buffer[j+7]


            c?.drawRect(upperLeft,upperTop+radius,upperRight,upperBottom-radius,mRenderGrey)
            c?.drawCircle(upperLeft+radius,upperTop+radius,radius,mRenderRed)
            c?.drawCircle(upperLeft+radius,upperBottom-radius,radius,mRenderBlue)

            //Log.d("testinging","radius is $radius")
            //c?.drawRoundRect(RectF(left, top, right, bottom),radius,radius,mRenderPaint)

            /*
            c?.drawRect(left,top+radius,right,bottom-radius,mRenderGrey)
            c?.drawCircle(left+radius,top+radius,radius,mRenderRed)
            c?.drawCircle(left+radius,bottom-radius,radius,mRenderBlue)

             */
        }
    }
}
