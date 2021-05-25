package ru.dbuzin.waviotapp.di

import android.app.Application
import dagger.Component
import ru.dbuzin.waviotapp.api.AuthApi
import ru.dbuzin.waviotapp.api.LkApi
import ru.dbuzin.waviotapp.app.AppDatabase
import ru.dbuzin.waviotapp.dao.CookiesDao
import ru.dbuzin.waviotapp.interceptors.AddCookiesInterceptor
import ru.dbuzin.waviotapp.presenters.AuthPresenter
import ru.dbuzin.waviotapp.presenters.DevicesFragmentPresenter
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [],
    modules = [AppModule::class, DatabaseModule::class, NetworkModule::class]
)
interface AppComponent {
    fun inject(authPresenter: AuthPresenter)
    fun inject(devicesFragmentPresenter: DevicesFragmentPresenter)
    fun inject(addCookiesInterceptor: AddCookiesInterceptor)

    fun cookiesDao(): CookiesDao
    fun authApi(): AuthApi
    fun lkApi(): LkApi

    fun appDataBase(): AppDatabase
    fun application(): Application
}