package com.example.stationgx.pages.manuelinput.bloodpressure

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stationgx.R
import com.example.stationgx.base.BaseActivity
import com.example.stationgx.pages.manuelinput.ManualInputActivity
import com.example.stationgx.ui.mpchart.BloodPressureBackgroundBarChart
import com.example.stationgx.ui.mpchart.BloodPressureBarChart
import com.github.mikephil.charting.data.BarData
import kotlinx.android.synthetic.main.dialog_blood_pressure_input.*
import kotlinx.android.synthetic.main.first_start_input.*
import kotlinx.android.synthetic.main.main_blood_pressure.*
import kotlin.collections.ArrayList

class BloodPressureActivity:BaseActivity(),View.OnClickListener {

    private var isFirst=true

    private lateinit var bpBarChart: BloodPressureBarChart

    private var barChart: BloodPressureBackgroundBarChart? =null
    private var barData: BarData?=null
    private lateinit var list:ArrayList<Entity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_blood_pressure)


        // todo 感覺可以用rxJava


        blood_pressure_back.setOnClickListener(this)
        blood_pressure_calendar.setOnClickListener(this)

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
            R.id.blood_pressure_calendar->{

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

            barChart?.addTargetZone(BloodPressureBackgroundBarChart.TargetZone(Color.parseColor("#F9EBEC"),100f,140f))
            barChart?.addTargetZone(BloodPressureBackgroundBarChart.TargetZone(Color.parseColor("#E5F6FF"),60f,100f))
            //barData?.barWidth=16f
            //Log.d("testinging","bar width is ${barData?.barWidth.toString()}")
            barChart?.data=barData

            val dataSet= barData!!.dataSets[0]

            val count=dataSet.entryCount

            Log.d("data!!","count is $count")

            list=ArrayList()
            for (i in 0 until count){

                var abnormal=false
                val diastolic=dataSet.getEntryForIndex(i).yVals[0].toInt()
                val systolic=(dataSet.getEntryForIndex(i).yVals[1]+diastolic).toInt()

                if (systolic>140){
                    abnormal=true
                }

                Log.d("data","sys $systolic and dia $diastolic")
                val bloodPressure= "$systolic / $diastolic "

                list.add(Entity("August 27th, 2020","18:00", bloodPressure,"blah blahbla ",abnormal))
            }

            val layoutManager=LinearLayoutManager(this)
            layoutManager.orientation=LinearLayoutManager.VERTICAL
            val dataList=findViewById<RecyclerView>(R.id.recyclerview_blood_pressure)
            dataList.layoutManager=layoutManager
            dataList.adapter=DataAdapter(list)
        }
        dialog.show()
    }
}