package ru.dbuzin.waviotapp.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.google.gson.Gson
import com.google.gson.JsonParser
import io.reactivex.MaybeObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import ru.dbuzin.waviotapp.api.LkApi
import ru.dbuzin.waviotapp.app.App
import ru.dbuzin.waviotapp.models.Device
import ru.dbuzin.waviotapp.models.Devices
import ru.dbuzin.waviotapp.views.DevicesFragmentView
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@InjectViewState
class DevicesFragmentPresenter: MvpPresenter<DevicesFragmentView>() {
    @Inject
    lateinit var lkApi: LkApi
    @Inject
    lateinit var gson: Gson

    init {
        App.getAppComponent().inject(this)
    }

    fun getDevicesList(){
        lkApi.getElementId()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : MaybeObserver<ResponseBody> {
                    override fun onSubscribe(@NonNull d: Disposable) {
                        viewState.showLoading()
                    }

                    override fun onSuccess(@NonNull response: ResponseBody) {
                        val elementId: String = JsonParser.parseString(response.string()).asJsonObject["element_id"].asString
                        lkApi.getFullElementInfo(elementId.toLong())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(object : MaybeObserver<Devices>{
                                    override fun onSubscribe(d: Disposable) {

                                    }

                                    override fun onSuccess(t: Devices) {
                                        var devicesList: MutableList<Device> = mutableListOf()
                                        t.devicesMap.forEach{(_, v) -> devicesList.add(v)}
                                        viewState.onSuccess(devicesList)
                                    }

                                    override fun onError(e: Throwable) {
                                        viewState.onError(e.message)
                                        e.printStackTrace()
                                    }

                                    override fun onComplete() {
                                    }
                                })
                    }

                    override fun onError(@NonNull e: Throwable) {
                        viewState.onError(e.message)
                        e.printStackTrace()
                    }

                    override fun onComplete() {}
                })
    }
}