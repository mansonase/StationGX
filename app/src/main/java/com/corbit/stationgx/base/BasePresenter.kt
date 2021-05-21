package com.corbit.stationgx.base

interface BasePresenter<T> {
    fun takeView(view:T)
    fun dropView()
}