package ru.dbuzin.waviotapp.api

import io.reactivex.Maybe
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import ru.dbuzin.waviotapp.models.Cookie
import ru.dbuzin.waviotapp.models.Login

interface AuthApi {
    @POST("/?action=user-login&true_api=1")
    @Headers("Content-type: application/json", "X-requested-with: XMLHttpRequest", "User-Agent: Waviot/Android")
    fun authentication(@Body login: Login) : Maybe<retrofit2.Response<Cookie>>
}