package ru.dbuzin.waviotapp.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import ru.dbuzin.waviotapp.app.AppDatabase
import ru.dbuzin.waviotapp.dao.CookiesDao
import javax.inject.Singleton


@Module
class DatabaseModule(mApplication: Application?) {
    private var appDatabase : AppDatabase =
        Room.databaseBuilder(mApplication!!, AppDatabase::class.java, "dbWaviot")
            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideDatabase(): AppDatabase {
        return appDatabase
    }

    @Singleton
    @Provides
    fun providesCookiesDao(appDataBase: AppDatabase): CookiesDao {
        return appDataBase.getCookiesDao()
    }
}