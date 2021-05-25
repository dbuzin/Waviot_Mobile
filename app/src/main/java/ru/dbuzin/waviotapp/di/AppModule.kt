package ru.dbuzin.waviotapp.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(application: Application) {
    private var mApplication: Application = application

    @Provides
    @Singleton
    fun providesApplication(): Application {
        return mApplication
    }
}