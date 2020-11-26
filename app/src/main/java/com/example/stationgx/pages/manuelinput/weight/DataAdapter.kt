package com.example.stationgx.pages.manuelinput.weight

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stationgx.R
import kotlinx.android.synthetic.main.list_blood_pressure_recyclerview.view.*

class DataAdapter(private val mData:List<Entity>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.list_weight_recyclerview,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder=holder as ViewHolder
        viewHolder.dateTV.text=mData[position].getDate()
        viewHolder.timeTV.text=mData[position].getTime()
        viewHolder.weightTV.text= mData[position].getWeight().toString()
        viewHolder.changeTV.text=mData[position].getChange().toString()
        viewHolder.targetTV.text=mData[position].getTarget().toString()
        viewHolder.bmiTV.text=mData[position].getBMI().toString()

        when(mData[position].getStatus()){
            "normal"->{
                viewHolder.statusTV.text="Normal"
                viewHolder.statusTV.setBackgroundResource(R.drawable.main_blue_four_15)
            }
            "slim"->{
                viewHolder.statusTV.text="Slim"
                viewHolder.statusTV.setBackgroundResource(R.drawable.main_pink_four_15)
            }
            "overweight"->{
                viewHolder.statusTV.text="Overweight"
                viewHolder.statusTV.setBackgroundResource(R.drawable.main_pink_four_15)
            }
        }
    }

    override fun getItemCount(): Int {
        //TODO("Not yet implemented")
        return mData.size
    }

    class ViewHolder(v:View):RecyclerView.ViewHolder(v){
        val dateTV:TextView=v.findViewById(R.id.list_weight_date)
        val timeTV:TextView=v.findViewById(R.id.list_weight_time)
        val weightTV:TextView=v.findViewById(R.id.list_weight)
        val changeTV:TextView=v.findViewById(R.id.list_weight_changes)
        val targetTV:TextView=v.findViewById(R.id.list_weight_target)
        val bmiTV:TextView=v.findViewById(R.id.list_weight_bmi)
        val statusTV:TextView=v.findViewById(R.id.list_weight_result)
    }
}