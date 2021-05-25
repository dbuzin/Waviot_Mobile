package ru.dbuzin.waviotapp.app

import android.app.Application
import ru.dbuzin.waviotapp.di.*

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .databaseModule(DatabaseModule(this))
            .networkModule(NetworkModule("https://auth.waviot.ru", "https://lk.waviot.ru"))
            .build()
    }

    companion object{
        private lateinit var appComponent: AppComponent
        fun getAppComponent(): AppComponent {
            return appComponent
        }
    }
}