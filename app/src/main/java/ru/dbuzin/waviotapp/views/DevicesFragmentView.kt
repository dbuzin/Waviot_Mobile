package ru.dbuzin.waviotapp.views

import com.arellomobile.mvp.MvpView
import ru.dbuzin.waviotapp.models.Device

interface DevicesFragmentView: MvpView {
    fun onSuccess(devices: MutableList<Device>)
    fun onError(error: String?)
    fun showLoading()
}