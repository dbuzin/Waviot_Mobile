package ru.dbuzin.waviotapp.presenters

import android.annotation.SuppressLint
import android.content.Context
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.google.android.material.textfield.TextInputEditText
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.schedulers.Schedulers
import ru.dbuzin.waviotapp.R
import ru.dbuzin.waviotapp.api.AuthApi
import ru.dbuzin.waviotapp.app.App
import ru.dbuzin.waviotapp.dao.CookiesDao
import ru.dbuzin.waviotapp.models.Cookie
import ru.dbuzin.waviotapp.models.Login
import ru.dbuzin.waviotapp.views.AuthView
import javax.inject.Inject

@InjectViewState
class AuthPresenter() : MvpPresenter<AuthView>() {
    @Inject
    lateinit var authApi: AuthApi
    @Inject
    lateinit var cookiesDao: CookiesDao
//TODO поменять на обсервбл
    private val observer : DisposableMaybeObserver<retrofit2.Response<Cookie>> = object: DisposableMaybeObserver<retrofit2.Response<Cookie>>(){
        override fun onSuccess(t: retrofit2.Response<Cookie>) {
            if(t.isSuccessful){
                cookiesDao.insert(t.body())
                    ?.subscribeOn(Schedulers.io())
                    ?.subscribe()
                viewState.onSuccess() //TODO поменять куки в бд и сделать сохранение
            }
            else
                viewState.onError(t.message())
        }

        override fun onError(e: Throwable) {
            viewState.onError(e.message)
        }

        override fun onComplete() {
        }

    }

    init {
        App.getAppComponent().inject(this)
    }

    fun authentication(login: String, password: String){
        viewState.showLoading()
        authApi.authentication(Login(login, password))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }

    fun textInputCheck(login: TextInputEditText, password: TextInputEditText, context: Context): Boolean{
//TODO сделать реактивным
        return if (login.text.isNullOrEmpty() || password.text.isNullOrEmpty()){
            login.error = context.getString(R.string.empty_field_error)
            password.error = context.getString(R.string.empty_field_error)
            false
        } else
            true
    }

    @SuppressLint("CheckResult")
    fun isAuthorized() {
        cookiesDao.getAll()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe { cookies ->
                if (!cookies!!.equals(null)) {
                    viewState.onSuccess()
                }
            }
    }
}