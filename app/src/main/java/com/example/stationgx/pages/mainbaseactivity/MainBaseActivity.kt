package com.example.stationgx.pages.mainbaseactivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.stationgx.R
import com.example.stationgx.base.BaseActivity
import com.example.stationgx.pages.mainbaseactivity.homefragment.HomeFragment
import com.example.stationgx.pages.mainbaseactivity.welcomefragment.WelcomeFragment
import kotlinx.android.synthetic.main.main_base.*
import javax.inject.Inject

class MainBaseActivity: BaseActivity(),MainBaseActivityContract.View {

    @Inject
    lateinit var presenter: MainBaseActivityPresenter

    //private lateinit var viewPager:ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_base)

        val page=intent.getBundleExtra(getString(R.string.bundle_between_activities))?.getInt(getString(R.string.fragment_indicator))

        val pagerAdapter=MainPagerAdapter(this)
        base_viewpager.adapter=pagerAdapter
        if (page!=null){
            base_viewpager.setCurrentItem(page,false)
        }
        pageindicators.count=2

        base_viewpager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                pageindicators.selection=position
                presenter.loadFragment(position)
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


    override fun onFragmentLoaded() {
        //TODO("Not yet implemented")
    }
}