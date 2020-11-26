package com.example.stationgx.pages.manuelinput.weight

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
import com.example.stationgx.ui.mpchart.weight.WeightBackgroundLineChart
import com.example.stationgx.ui.mpchart.weight.WeightLineChart
import com.github.mikephil.charting.data.LineData
import kotlinx.android.synthetic.main.dialog_blood_pressure_input.*
import kotlinx.android.synthetic.main.first_start_input.*
import kotlinx.android.synthetic.main.main_blood_pressure.*
import kotlinx.android.synthetic.main.main_blood_pressure.calendar_text
import kotlinx.android.synthetic.main.main_weight.*
import kotlin.collections.ArrayList

class WeightActivity:BaseActivity(),View.OnClickListener {

    private var isFirst=true

    private lateinit var duration:String

    private var weightLineChart: WeightLineChart?=null

    private var lineChart: WeightBackgroundLineChart? =null
    private var lineData: LineData?=null
    private lateinit var list:ArrayList<Entity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_weight)


        // todo 感覺可以用rxJava


        weight_back.setOnClickListener(this)
        weight_calendar.setOnClickListener(this)

        if (isFirst){
            createFirstDialog()
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.calendar_left_arrow->{
                //todo 向左翻頁
            }
            R.id.calendar_right_arrow->{
                //todo 向右翻頁
            }
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
            R.id.weight_back->{
                Log.d("testing","clicked in weight")
                val intent=Intent(this,ManualInputActivity::class.java)
                startActivity(intent)
            }
            R.id.blood_pressure_calendar -> {
                val fragment = WeightCalendarFragment()
                val bundle = Bundle()
                bundle.putString("range",duration)
                fragment.arguments=bundle
                fragment.show(supportFragmentManager, "test")
                Log.d("where", "show the process here")
            }
        }
    }

    private fun createFirstDialog(){
        val dialog=Dialog(this)
        dialog.setContentView(R.layout.first_start_input)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
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
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCanceledOnTouchOutside(false)
        dialog.blood_pressure_dialog_clear.setOnClickListener { dialog.dismiss() }
        dialog.input_blood_pressure_save.setOnClickListener {
            isFirst=false

            //todo save input
            dialog.dismiss()

            duration="today"
            doFakeData(duration)
        }
        dialog.show()
    }

    fun selectDuration(duration:String){
        this.duration=duration
        Log.d("WA","whaaaaaaaaaaaaaaaaaaa$duration")
        doFakeData(this.duration)
        calendar_text.text = this.duration
    }

    private fun doFakeData(duration: String){
        weightLineChart=null
        weightLineChart= WeightLineChart(findViewById(R.id.blood_pressure_barchart),duration)
        lineChart?.clear()
        lineChart=weightLineChart!!.getChart()
        lineData=weightLineChart!!.getData()

        //lineChart?.addTargetZone(WeightBackgroundLineChart.TargetZone(Color.parseColor("#F9EBEC"),100f,140f))
        lineChart?.addTargetZone(WeightBackgroundLineChart.TargetZone(Color.parseColor("#C0E3F4"),55f,65f))
        //barData?.barWidth=16f
        //Log.d("testinging","bar width is ${barData?.barWidth.toString()}")
        lineChart?.data=lineData

        val dataSet= lineData!!.dataSets[0]

        val count=dataSet.entryCount

        Log.d("data!!","count is $count")

        list=ArrayList()
        for (i in 0 until count){

            var status="normal"
            val weight=dataSet.getEntryForIndex(i).y
            //val systolic=(dataSet.getEntryForIndex(i).yVals[1]+weight).toInt()

            if (weight>65f){
                status="overweight"
            }

            Log.d("data","weight is $weight ")
            val weightTx= weight.toString()

            val changeTx=0.4f
            val bmiTx=21.9f
            val targetTx=55f

            list.add(Entity("August 27th, 2020","18:00", weight,changeTx,targetTx,bmiTx,status))
        }

        val layoutManager=LinearLayoutManager(this)
        layoutManager.orientation=LinearLayoutManager.VERTICAL
        val dataList=findViewById<RecyclerView>(R.id.recyclerview_blood_pressure)
        dataList.layoutManager=layoutManager
        dataList.adapter=DataAdapter(list)
    }
}