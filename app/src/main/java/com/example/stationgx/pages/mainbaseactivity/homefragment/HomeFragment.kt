package com.example.stationgx.pages.mainbaseactivity.homefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.stationgx.R
import com.example.stationgx.pages.BaseFragment
import java.util.*
import javax.inject.Inject

class HomeFragment:BaseFragment(),HomeFragmentContract.View {

    @Inject
    lateinit var presenter:HomeFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home,container,false)
    }

    override fun showDate(current: Date) {
        //TODO("Not yet implemented")
    }

    override fun showLastDocumenting(current: Date) {
        //TODO("Not yet implemented")
    }

    override fun showNotificationRedPoint(numbers: Int) {
        //TODO("Not yet implemented")
    }

    override fun showName(name: String) {
        //TODO("Not yet implemented")
    }

    override fun showLocation(location: String) {
        //TODO("Not yet implemented")
    }
}