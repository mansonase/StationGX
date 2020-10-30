package com.example.stationgx.pages.manuelinput.bloodpressure

import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.stationgx.R
import com.example.stationgx.base.BaseActivity
import com.example.stationgx.pages.manuelinput.ManualInputActivity
import com.example.stationgx.ui.mpchart.BloodPressureBarChart
import com.example.stationgx.ui.mpchart.BloodPressureRenderer
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import kotlinx.android.synthetic.main.dialog_blood_pressure_input.*
import kotlinx.android.synthetic.main.first_start_input.*
import kotlinx.android.synthetic.main.main_blood_pressure.*

class BloodPressureActivity:BaseActivity(),View.OnClickListener {

    private var isFirst=true

    private lateinit var bpBarChart: BloodPressureBarChart

    private var barChart: BarChart? =null
    private var barData: BarData?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_blood_pressure)


        // todo 感覺可以用rxJava



        blood_pressure_back.setOnClickListener(this)

        if (isFirst){
            createFirstDialog()
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            /*
            R.id.blood_pressure_start_input->{
                Log.d("testing","clicked input")
                val dialog=Dialog(this)
                dialog.setContentView(R.layout.dialog_blood_pressure_input)
                dialog.blood_pressure_dialog_clear.setOnClickListener { dialog.dismiss() }
                dialog.input_blood_pressure_save.setOnClickListener { dialog.dismiss() }
                dialog.show()
            }

             */
            R.id.blood_pressure_back->{
                Log.d("testing","clicked in blood pressure")
                val intent=Intent(this,ManualInputActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun createFirstDialog(){
        val dialog=Dialog(this)
        dialog.setContentView(R.layout.first_start_input)
        dialog.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        dialog.setCanceledOnTouchOutside(false)
        dialog.fist_input.setOnClickListener {
            dialog.dismiss()
            createInputDialog()
        }
        dialog.show()
    }

    private fun createInputDialog(){
        val dialog=Dialog(this)
        dialog.setContentView(R.layout.dialog_blood_pressure_input)
        dialog.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        dialog.setCanceledOnTouchOutside(false)
        dialog.blood_pressure_dialog_clear.setOnClickListener { dialog.dismiss() }
        dialog.input_blood_pressure_save.setOnClickListener {
            isFirst=false

            //todo save input
            dialog.dismiss()
            bpBarChart= BloodPressureBarChart(findViewById(R.id.blood_pressure_barchart),"week")
            barChart=bpBarChart.getChart()
            barData=bpBarChart.getData()
            //barData?.barWidth=16f
            //Log.d("testinging","bar width is ${barData?.barWidth.toString()}")
            barChart?.data=barData

        }
        dialog.show()
    }
}