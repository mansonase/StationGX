package com.example.stationgx.pages.mainbaseactivity.welcomefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stationgx.R
import com.example.stationgx.base.BaseFragment
import com.example.stationgx.data.prefs.SP
import com.example.stationgx.data.prefs.SharedPreferencesHelper
import javax.inject.Inject

class WelcomeFragment: BaseFragment(),WelcomeFragmentContract.View {

    @Inject
    lateinit var presenter:WelcomeFragmentPresenter

    @Inject
    lateinit var sp: SharedPreferencesHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        sp.putStringItem("testAAA","hihihi")
        return inflater.inflate(R.layout.fragment_wellcome,container,false)
    }

    override fun showFlowerAnimation() {
        //TODO("Not yet implemented")
    }

    override fun showDate() {
        //TODO("Not yet implemented")
    }

    override fun showMoney() {
        //TODO("Not yet implemented")
    }

    override fun showWater() {
        //TODO("Not yet implemented")
    }

    override fun showFertile() {
        //TODO("Not yet implemented")
    }

    override fun showWeather() {
        //TODO("Not yet implemented")
    }
}