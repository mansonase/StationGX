package com.corbit.stationgx.ui.mpchart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import com.github.mikephil.charting.charts.BarChart

class BloodPressureBackgroundBarChart:BarChart {

    lateinit var mPaint: Paint
    lateinit var path: Path
    lateinit var mPaintRec:Paint

    private var mTargetZones=ArrayList<TargetZone>()
    private val pts= FloatArray(4)

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    override fun init() {
        super.init()
        mPaint= Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint.style=Paint.Style.FILL
        path= Path()
        mPaintRec= Paint(Paint.ANTI_ALIAS_FLAG)
        mPaintRec.style=Paint.Style.STROKE
        mPaintRec.strokeWidth=2f
        mPaintRec.color=Color.parseColor("#707070")
    }

    override fun onDraw(canvas: Canvas?) {
        //super.onDraw(canvas)

        for(targetZone in mTargetZones){
            pts[1]=targetZone.lowerLimit
            pts[3]=targetZone.upperLimit
            mLeftAxisTransformer.pointValuesToPixel(pts)

            mPaint.color = targetZone.color
            canvas?.drawRect(mViewPortHandler.contentLeft(),pts[1],mViewPortHandler.contentRight(),pts[3],mPaint)
        }

        path.addRoundRect(mViewPortHandler.contentLeft(),1f,mViewPortHandler.contentRight(),399f,15f,15f,Path.Direction.CCW)
        canvas?.drawPath(path,mPaintRec)
        //make path only draw inside the chart
        path= Path()

        super.onDraw(canvas)
    }

    fun addTargetZone(targetZone: TargetZone){
        mTargetZones.add(targetZone)
    }
    fun getTargetZone():List<TargetZone>{
        return mTargetZones
    }
    fun clearTargetZone(){
        mTargetZones= ArrayList<TargetZone>()
    }

    class TargetZone(val color: Int, val lowerLimit: Float, val upperLimit: Float) {
    }
}