package com.example.stationgx.pages

interface BaseView {

    fun showMessage(id:Int)
    fun showMessage(text:CharSequence)
    fun showLoading()
    fun showLoading(content: CharSequence)
    fun cancelLoading()

}