package com.example.stationgx.base

interface BasePresenter<T> {
    fun takeView(view:T)
    fun dropView()
}