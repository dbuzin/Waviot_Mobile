package ru.dbuzin.waviotapp.app

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.dbuzin.waviotapp.dao.CookiesDao
import ru.dbuzin.waviotapp.models.Cookie

@Database(entities = (arrayOf(Cookie::class)), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getCookiesDao() : CookiesDao
}