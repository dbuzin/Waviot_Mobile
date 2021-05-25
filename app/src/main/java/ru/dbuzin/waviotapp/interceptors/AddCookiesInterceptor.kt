package ru.dbuzin.waviotapp.interceptors

import android.annotation.SuppressLint
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.internal.observers.BlockingObserver
import io.reactivex.internal.operators.observable.BlockingObservableMostRecent
import io.reactivex.internal.operators.observable.ObservableBlockingSubscribe
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.Response
import ru.dbuzin.waviotapp.app.App
import ru.dbuzin.waviotapp.dao.CookiesDao
import ru.dbuzin.waviotapp.models.Cookie
import javax.inject.Inject

class AddCookiesInterceptor: Interceptor {

    @Inject
    lateinit var cookiesDao: CookiesDao

    init {
        App.getAppComponent().inject(this)
    }

    @SuppressLint("CheckResult")
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader("Cookie", "WAVIOT_JWT=" + cookiesDao.getAll().jwt)
        print("Interceptor" + Thread.currentThread().name)
        return chain.proceed(builder.build())
    }
}