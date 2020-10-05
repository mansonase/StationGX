package com.example.stationgx.pages.mainbaseactivity.welcomefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.stationgx.R
import com.example.stationgx.pages.BaseFragment

class WelcomeFragment:BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_wellcome,container,false)
    }
}