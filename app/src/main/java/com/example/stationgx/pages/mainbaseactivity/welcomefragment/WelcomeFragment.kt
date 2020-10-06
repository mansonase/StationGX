package com.example.stationgx.pages.mainbaseactivity.welcomefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.stationgx.R
import com.example.stationgx.pages.BaseFragment
import javax.inject.Inject

class WelcomeFragment:BaseFragment(),WelcomeFragmentContract.View {

    @Inject
    lateinit var presenter:WelcomeFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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