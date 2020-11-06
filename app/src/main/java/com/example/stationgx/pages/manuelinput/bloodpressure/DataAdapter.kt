package com.example.stationgx.pages.manuelinput.bloodpressure

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stationgx.R
import kotlinx.android.synthetic.main.list_blood_pressure_recyclerview.view.*

class DataAdapter(private val mData:List<Entity>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.list_blood_pressure_recyclerview,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder=holder as ViewHolder
        viewHolder.dateTV.text=mData[position].getDate()
        viewHolder.timeTV.text=mData[position].getTime()
        viewHolder.bpTV.text= mData[position].getBloodPressure()
        viewHolder.noteTV.text=mData[position].getNote()
        if (mData[position].getAbnormal()){
            viewHolder.statusTV.text=" Abnormal "
            viewHolder.statusTV.setBackgroundResource(R.drawable.main_pink_four_15)

        }else{
            viewHolder.statusTV.text=" Normal "
            viewHolder.statusTV.setBackgroundResource(R.drawable.main_blue_four_15)
        }
    }

    override fun getItemCount(): Int {
        //TODO("Not yet implemented")
        return mData.size
    }

    class ViewHolder(v:View):RecyclerView.ViewHolder(v){
        val dateTV:TextView=v.findViewById(R.id.list_blood_pressure_date)
        val timeTV:TextView=v.findViewById(R.id.list_blood_pressure_time)
        val bpTV:TextView=v.findViewById(R.id.list_blood_pressure_sys_dia)
        val noteTV:TextView=v.findViewById(R.id.list_blood_pressure_note)
        val statusTV:TextView=v.findViewById(R.id.list_blood_pressure_result)
    }
}