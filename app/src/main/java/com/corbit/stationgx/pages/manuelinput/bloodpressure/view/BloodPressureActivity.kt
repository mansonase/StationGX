package com.corbit.stationgx.pages.manuelinput.bloodpressure.view

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.corbit.stationgx.R
import com.corbit.stationgx.base.BaseActivity
import com.corbit.stationgx.pages.manuelinput.ManualInputActivity
import com.corbit.stationgx.pages.manuelinput.bloodpressure.presenter.BloodPressurePresenter
import com.corbit.stationgx.pages.manuelinput.bloodpressure.presenter.Contract
import com.corbit.stationgx.ui.custom.calendar.CalendarUtil
import com.corbit.stationgx.ui.mpchart.BloodPressureBackgroundBarChart
import com.corbit.stationgx.ui.mpchart.BloodPressureBarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarEntry
import kotlinx.android.synthetic.main.dialog_blood_pressure_input.*
import kotlinx.android.synthetic.main.first_start_input.*
import kotlinx.android.synthetic.main.main_blood_pressure.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class BloodPressureActivity:BaseActivity(),View.OnClickListener,Contract.IView<Entity,BarEntry> {

    private var isFirst=true

    private lateinit var duration:String
    private var select_day:String?=null

    private var bpBarChart: BloodPressureBarChart?=null

    private var transaction:FragmentTransaction?=null
    private var fragmentCalendar: BPCalendarFragment?=null

    private var barChart: BloodPressureBackgroundBarChart? =null
    private var barData: BarData?=null
    private lateinit var list:ArrayList<Entity>
    private var barEntryList=ArrayList<BarEntry>()
    private var startTime=0

    private lateinit var mPresenter:Contract.IPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_blood_pressure)


        // todo 感覺可以用rxJava


        blood_pressure_back.setOnClickListener(this)
        blood_pressure_calendar.setOnClickListener(this)

        if (isFirst){
            createFirstDialog()
        }
        mPresenter=BloodPressurePresenter(this)
    }

    override fun updateChart(arrayList: ArrayList<BarEntry>) {
        presentChart(duration,arrayList)
        barEntryList=arrayList
    }

    override fun updateTable(list: ArrayList<Entity>) {
        presentTable(list)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.calendar_left_arrow->{
                //todo 向左翻頁
                startTime=getStartTime(true,"day",startTime)
                mPresenter.getDayData(startTime)
            }
            R.id.calendar_right_arrow->{
                //todo 向右翻頁
                startTime=getStartTime(false,"day",startTime)
                mPresenter.getDayData(startTime)
            }

            R.id.input_blood_pressure->{
                createInputDialog()
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

            R.id.blood_pressure_back->{
                Log.d("testing","clicked in blood pressure")

                val bundle=Bundle()
                bundle.putString("value","115/85")
                val intent=Intent(this,ManualInputActivity::class.java)
                intent.putExtra("bloodpressure",bundle)
                startActivity(intent)
            }
            R.id.blood_pressure_calendar -> {
                if (fragmentCalendar==null){
                    fragmentCalendar= BPCalendarFragment()
                    Log.d("bpactivity","only once??")
                }
                transaction= supportFragmentManager.beginTransaction()
                val bundle = Bundle()
                //bundle.putString("range",duration)
                bundle.putString("range","week")
                CalendarUtil.duration="week"
                val arrayListCalendar=ArrayList<Calendar>()
                val calendar=Calendar.getInstance()
                calendar.timeInMillis=startTime.toLong()*1000
                arrayListCalendar.add(calendar)

                bundle.putSerializable("calendar",arrayListCalendar)
                bundle.putSerializable("barentry",barEntryList)

                fragmentCalendar!!.arguments=bundle
                //fragment.show(supportFragmentManager, "test")

                if (fragmentCalendar!!.isAdded){
                    transaction!!.show(fragmentCalendar!!)
                    Log.d("bpactivity","show")
                }else {
                    transaction!!.add(fragmentCalendar!!, "calendar")
                    Log.d("bpactivity","add?")
                }
                transaction!!.commit()
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

            duration=CalendarUtil.day
            //doFakeData(duration)
        }
        dialog.show()
    }

    private fun createInputDialog(){

        val calendar=Calendar.getInstance()
        val formatDate=SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
        val formatTime=SimpleDateFormat("aa HH:mm",Locale.getDefault())


        val dialog=Dialog(this)
        dialog.setContentView(R.layout.dialog_blood_pressure_input)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCanceledOnTouchOutside(false)

        dialog.dialog_date.text=formatDate.format(calendar.time)
        dialog.dialog_time.text=formatTime.format(calendar.time)
        dialog.dialog_date.setOnClickListener {

           DatePickerDialog(this,R.style.PickerTheme, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->


               calendar.set(year,month,dayOfMonth)
               dialog.dialog_date.text=formatDate.format(calendar.time)


           },calendar.get(Calendar.YEAR)
           ,calendar.get(Calendar.MONTH)
           ,calendar.get(Calendar.DAY_OF_MONTH )).show()
        }
        dialog.dialog_time.setOnClickListener {

            TimePickerDialog(this,R.style.PickerTheme, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->

                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay)
                calendar.set(Calendar.MINUTE,minute)
                dialog.dialog_time.text=formatTime.format(calendar.time)

            },calendar.get(Calendar.HOUR_OF_DAY)
            ,calendar.get(Calendar.MINUTE)
            ,true).show()

        }

        dialog.blood_pressure_dialog_clear.setOnClickListener {
            dialog.dismiss()
            //startTime=getThisTodayStartTime()
            startTime=getThisWeekStartTime()
            mPresenter.getDayData(startTime)
        }
        dialog.input_blood_pressure_save.setOnClickListener {
            isFirst=false


            //todo save input

            if (checkIsBloodPressureValid(dialog)){
                if (checkIsBloodPressureInRange(dialog)){
                    saveBloodPressureData(dialog)
                }else{
                    presentOutOfRangeAlert()
                }
            }else{
                presentBloodPressureInvalidAlert()
            }

            dialog.dismiss()


            Log.d("createinputdialog","${dialog.input_systolic.text}, date is ${dialog.dialog_date.text}")
            duration=CalendarUtil.day

            startTime=getThisTodayStartTime()
            mPresenter.getDayData(startTime)

        }
        dialog.show()
    }

    fun selectDuration(duration:String){
        this.duration=duration
        Log.d("BPA","whaaaaaaaaaaaaaaaaaaa$duration")
        //doFakeData(this.duration)
        calendar_text.text = this.duration

    }

    private fun checkIsBloodPressureValid(dialog: Dialog):Boolean{
        val systolicNum=dialog.input_systolic.text.toString().toDouble()
        val diastolicNum=dialog.input_diastolic.text.toString().toDouble()
        return systolicNum>diastolicNum
    }

    private fun checkIsBloodPressureInRange(dialog: Dialog):Boolean{
        val systolicNum=dialog.input_systolic.text.toString().toDouble()
        val systolicValid= systolicNum in 90.0..140.0
        val diastolicNum=dialog.input_diastolic.text.toString().toDouble()
        val diastolicValid=diastolicNum in 60.0..90.0

        return systolicValid&&diastolicValid
    }
    private fun presentOutOfRangeAlert(){

        android.app.AlertDialog.Builder(this)
                .setTitle(R.string.common_alert)
                .setMessage(R.string.manual_input_outofrange_blood_pressure)
                .setPositiveButton(R.string.common_ok){dialog,which->

                    saveBloodPressureData(dialog as Dialog)
                }
                .setNegativeButton(R.string.common_cancel){dialog,which->}
                .show()
    }
    private fun presentBloodPressureInvalidAlert(){
        android.app.AlertDialog.Builder(this)
                .setTitle(R.string.common_alert)
                .setMessage(R.string.manual_input_invalid_blood_pressure)
                .setPositiveButton(R.string.common_ok) { dialog, which -> }
                .show()
    }

    private fun saveBloodPressureData(dialog: Dialog){


        val calendar=Calendar.getInstance()

        val timeText=dialog.dialog_date.text.toString()+" "+dialog.dialog_time.text.toString()
        val format=SimpleDateFormat("MMMM dd, yyyy aa HH:mm",Locale.getDefault())

        calendar.time=format.parse(timeText)

Log.d("yeahyeahyeah",timeText)
        mPresenter.saveData(calendar.timeInMillis, dialog.input_systolic.text.toString().toInt(), dialog.input_diastolic.text.toString().toInt(), (calendar.timeInMillis/1000).toInt(), "")

/*

        if (realm==null)return

        realm!!.beginTransaction()
        val bp= realm!!.createObject(BloodPressureBean::class.java)
        bp.keyIndex=Utils.createKeyIndex()
        // TODO: 2021/5/21 key/index如何產生? 由本地端或後端取得? 再討論
        bp.systolic=dialog.input_systolic.text.toString().toInt()
        bp.diastolic=dialog.input_diastolic.text.toString().toInt()

        val cal=Calendar.getInstance()
        val format=SimpleDateFormat("MMMM dd, yyyy aa HH:mm", Locale.getDefault())
        val date_Time=dialog.dialog_date.text.toString()+" "+dialog.dialog_time.text.toString()
        cal.time=format.parse(date_Time)

         */

       /*
        bp.year=cal.get(Calendar.YEAR)
        bp.month=cal.get(Calendar.MONTH)
        bp.day=cal.get(Calendar.DAY_OF_MONTH)
        bp.hour=cal.get(Calendar.HOUR_OF_DAY)
        bp.minute=cal.get(Calendar.MINUTE)

        */

        // TODO: 2021/5/21 還有note還沒save


        //realm!!.commitTransaction()
    }

/*
    private fun datePicker():Calendar{

        val calendar=Calendar.getInstance()
        val year=calendar.get(Calendar.YEAR)
        val month=calendar.get(Calendar.MONTH)
        val day=calendar.get(Calendar.DAY_OF_MONTH)

        val dialog=DatePickerDialog(this, { view, year, month, dayOfMonth ->

            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            select_day = format.format(calendar.time)


        }, year, month, day)
        dialog.datePicker.maxDate=calendar.timeInMillis
        dialog.show()
        return calendar
    }

 */



    private fun presentChart(duration: String, arrayList: ArrayList<BarEntry>){
        bpBarChart=null
        bpBarChart= BloodPressureBarChart(findViewById(R.id.blood_pressure_barchart),duration)
        barChart?.clear()
        bpBarChart?.setBarEntry(arrayList)

        when(duration){
            CalendarUtil.day->bpBarChart?.setStartTime(startTime)
            CalendarUtil.week->bpBarChart?.setStartTime(startTime)
            CalendarUtil.month->bpBarChart?.setStartTime(startTime)
        }

        barChart=bpBarChart!!.getChart()
        barData=bpBarChart!!.getData()

        barChart?.addTargetZone(BloodPressureBackgroundBarChart.TargetZone(Color.parseColor("#F9EBEC"),100f,140f))
        barChart?.addTargetZone(BloodPressureBackgroundBarChart.TargetZone(Color.parseColor("#E5F6FF"),60f,100f))

        barChart?.data=barData

    }
    private fun presentTable(arrayList: ArrayList<Entity>){
        val layoutManager=LinearLayoutManager(this)
        layoutManager.orientation=LinearLayoutManager.VERTICAL
        val dataList=findViewById<RecyclerView>(R.id.recyclerview_blood_pressure)
        dataList.layoutManager=layoutManager
        dataList.adapter=DataAdapter(arrayList)
    }

    private fun doFakeData(duration: String){
        bpBarChart=null
        bpBarChart= BloodPressureBarChart(findViewById(R.id.blood_pressure_barchart),duration)
        barChart?.clear()
        barChart=bpBarChart!!.getChart()
        barData=bpBarChart!!.getData()

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
        dataList.adapter= DataAdapter(list)
    }


    private fun getThisTodayStartTime():Int{

        val calendar=Calendar.getInstance()
        calendar.clear(Calendar.MILLISECOND)
        calendar.set(Calendar.HOUR_OF_DAY,0)
        calendar.set(Calendar.MINUTE,0)
        calendar.set(Calendar.SECOND,0)


        return (calendar.timeInMillis/1000).toInt()

    }


    /***
     * DAY_OF_WEEK: Sunday=1
     *              Monday=2
     *              Tuesday=3
     *              Wednesday=4
     *              Thursday=5
     *              Friday=6
     *              Saturday=7
     *
     */
    private fun getThisWeekStartTime():Int{

        val calendar=Calendar.getInstance()
        calendar.clear(Calendar.MILLISECOND)

        calendar.firstDayOfWeek=Calendar.MONDAY
        calendar.set(Calendar.DAY_OF_WEEK,calendar.firstDayOfWeek)
        calendar.set(Calendar.HOUR_OF_DAY,0)
        calendar.set(Calendar.MINUTE,0)
        calendar.set(Calendar.SECOND,0)

        Log.d("bpcalendarNew","in function ${calendar.timeInMillis}")
        return (calendar.timeInMillis/1000).toInt()
    }

    private fun getThisMonthStartTime():Int{

        val calendar=Calendar.getInstance()
        calendar.clear(Calendar.MILLISECOND)

        calendar.set(Calendar.DAY_OF_MONTH,1)
        calendar.set(Calendar.HOUR_OF_DAY,0)
        calendar.set(Calendar.MINUTE,0)
        calendar.set(Calendar.SECOND,0)

        return (calendar.timeInMillis/1000).toInt()
    }

    private fun getStartTime(last:Boolean,duration:String,start:Int):Int{

        val calendar=Calendar.getInstance()
        calendar.timeInMillis=start.toLong()*1000


       val diff = if (last){
            -1
        }else{
            1
        }

        when(duration){

            CalendarUtil.day->{
                calendar.add(Calendar.DAY_OF_MONTH,diff)
            }

            CalendarUtil.week->{
                calendar.add(Calendar.DAY_OF_MONTH,diff*7)
            }

            CalendarUtil.month->{
                calendar.add(Calendar.MONTH,diff)
            }
        }

        return (calendar.timeInMillis/1000).toInt()
    }





}