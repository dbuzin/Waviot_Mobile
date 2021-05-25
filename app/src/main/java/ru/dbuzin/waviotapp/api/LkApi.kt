package ru.dbuzin.waviotapp.api

import io.reactivex.Maybe
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import ru.dbuzin.waviotapp.models.Devices

interface LkApi {
    @GET("/api.tree/get_element/")
    @Headers("Content-type: application/json", "X-requested-with: XMLHttpRequest", "User-Agent: Waviot/Android")
    fun getElementId(): Maybe<ResponseBody>

    @GET("/api.data/get_full_element_info/")
    @Headers("Content-type: application/json", "X-requested-with: XMLHttpRequest", "User-Agent: Waviot/Android")
    fun getFullElementInfo(@Query("id") id: Long): Maybe<Devices>
}