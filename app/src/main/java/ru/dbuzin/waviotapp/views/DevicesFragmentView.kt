package ru.dbuzin.waviotapp.views

import com.arellomobile.mvp.MvpView

interface DevicesFragmentView: MvpView {
    fun onSuccess()
    fun onError(error: String?)
    fun showLoading()
}