package ru.dbuzin.waviotapp.api

import io.reactivex.Maybe
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface LkApi {
    @GET("/api.tree/get_element/")
    @Headers("Content-type: application/json", "X-requested-with: XMLHttpRequest", "User-Agent: Waviot/Android")
    fun getElementId(): Maybe<ResponseBody>

    @GET("/api.data/get_full_element_info/")
    @Headers("Content-type: application/json", "X-requested-with: XMLHttpRequest", "User-Agent: Waviot/Android", "Cookie: WAVIOT_JWT=eyJhbGciOiJtZDUiLCJ0eXAiOiJXQVZJT1RfSldUIn0=.eyJpc3MiOiJhdXRoLndhdmlvdC5ydSIsImF1ZCI6IndhdmlvdC5ydSIsImV4cCI6MTYyMjI5NzQwMywiaGVhZEVtYWlsIjoidGVzdDF0ZXN0QGdtYWlsLmNvbSIsImNsYWltcyI6W10sImFjY291bnRfaWQiOiJlY2JkN2VlNC1iZmViLTQwOGMtYTk4OC0zZTk2NmQyODQ5N2YiLCJsYXN0TmFtZSI6IlRlc3QiLCJmaXJzdE5hbWUiOiJXYXZpb3QiLCJtaWRkbGVOYW1lIjpmYWxzZSwidGltZXpvbmUiOiJFdXJvcGVcL01vc2NvdyIsImxrX2lkIjoxMjQ0OCwicmFiYml0X2d1ZXN0X2FwaV9jb25uZWN0aW9uIjoiYW1xcHM6XC9cL2FwaV9ndWVzdDp3YXZpb3RAci53YXZpb3QucnVcL1wvIiwibGFuZ3VhZ2UiOiJydSIsImJfaWQiOjEwNDI0LCJhcGlLZXlzIjpbIjdtMDZxNG51aGhjYnVjc2VoZ2N0aGNvcjdkOGh4YzdmdWpjMDY3MWticnhxcG53eDAyMG5tNmVzNGVldnF0ODAiXX0=.NTI1NjZlY2IwNDUzYTA5OGNjOWEwMGYyMGExOTE3ODQ=")
    fun getFullElementInfo(@Query("id") id: Long): Maybe<ResponseBody>
}