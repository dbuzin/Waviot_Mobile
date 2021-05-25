package ru.dbuzin.waviotapp.views

import com.arellomobile.mvp.MvpView

interface AuthView : MvpView{
    fun onSuccess()
    fun onSuccessRecovery(message: String?)
    fun onError(error: String?)
    fun showLoading()
}