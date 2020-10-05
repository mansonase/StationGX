package com.example.stationgx.pages

interface BasePresenter<T> {
    fun takeView(view:T)
    fun dropView()
}