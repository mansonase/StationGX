package com.corbit.stationgx.ui.custom.calendar.weight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.corbit.stationgx.R
import com.corbit.stationgx.pages.manuelinput.weight.WeightCalendarFragment

class FragmentToday:Fragment(),View.OnClickListener{
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.fragment_calendar_today,container,false)
        view.findViewById<ImageView>(R.id.calendar_today_left).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.calendar_today_right).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.calendar_today_return).setOnClickListener(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.calendar_today_left->{
                //todo 向左翻頁, 昨天? 上個月?
            }
            R.id.calendar_today_right->{
                //todo 向右翻頁, 明天? 下個月?
            }
            R.id.calendar_today_return->{
                //todo 回switcher fragment
                (parentFragment as WeightCalendarFragment).controlCalendar("switcher","today")
            }
        }
    }
}