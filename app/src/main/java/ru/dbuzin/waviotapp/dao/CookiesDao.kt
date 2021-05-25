package ru.dbuzin.waviotapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Maybe
import ru.dbuzin.waviotapp.models.Cookie

@Dao
interface CookiesDao {
    @Query("SELECT * FROM Cookie")
    fun getAll(): Maybe<Cookie>

    @Query("DELETE FROM Cookie")
    fun delete(): Maybe<Int?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cookie: Cookie?): Maybe<Long?>?
}