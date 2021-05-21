package com.corbit.stationgx.base


import android.app.Activity
import dagger.android.support.DaggerFragment

open class BaseFragment: DaggerFragment(), BaseView {

   private fun getBaseActivity(): BaseActivity?{
        val activity=Activity()
        if (activity is BaseActivity){
            return activity
        }
        return null
    }

    override fun showMessage(id: Int) {
        getBaseActivity()?.showMessage(id)
    }

    override fun showMessage(text: CharSequence) {
        getBaseActivity()?.showMessage(text)
    }

    override fun showLoading() {
        getBaseActivity()?.showLoading()
    }

    override fun showLoading(content: CharSequence) {
        getBaseActivity()?.showLoading(content)
    }

    override fun cancelLoading() {
        getBaseActivity()?.cancelLoading()
    }
}