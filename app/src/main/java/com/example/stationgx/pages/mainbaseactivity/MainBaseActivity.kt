package com.example.stationgx.pages.mainbaseactivity

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.stationgx.R
import com.example.stationgx.pages.BaseActivity
import com.example.stationgx.pages.home.HomeFragment
import com.example.stationgx.pages.welcome.WelcomeFragment
import kotlinx.android.synthetic.main.main_base.*
import javax.inject.Inject

class MainBaseActivity: BaseActivity() {

    @Inject
    lateinit var shared:SharedPreferences

    //private lateinit var viewPager:ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_base)

        val isSaved= shared.edit().putString("mainbaseact","what is that").commit()

        if (isSaved)
            Log.d("main base","got return true")

        val pagerAdapter=MainPagerAdapter(this)
        base_viewpager.adapter=pagerAdapter

        pageindicators.count=2

        base_viewpager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                pageindicators.selection=position
            }
        })
    }



    private inner class MainPagerAdapter(mainBaseActivity: MainBaseActivity) :FragmentStateAdapter(mainBaseActivity){
        override fun getItemCount(): Int {
            return 2
        }

        override fun createFragment(position: Int): Fragment {
            when(position){
                0->{
                    pageindicators.selection=0
                    return WelcomeFragment()
                }
                1->{
                    pageindicators.selection=1
                    return HomeFragment()
                }
            }
            return HomeFragment()
        }
    }

}