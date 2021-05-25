package ru.dbuzin.waviotapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.dbuzin.waviotapp.api.AuthApi
import ru.dbuzin.waviotapp.api.LkApi
import ru.dbuzin.waviotapp.interceptors.AddCookiesInterceptor
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule(authUrl: String, lkUrl: String) {
   var authUrl: String = authUrl
   var lkUrl: String = lkUrl

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    @Named("Clean")
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(authUrl)
            .build()
    }
    @Provides
    @Singleton
    fun provideClient(): OkHttpClient{
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(AddCookiesInterceptor())
            .build()
    }

    @Provides
    @Singleton
    @Named("WithCookie")
    fun provideRetrofitWithCookie(gson: Gson, client: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .baseUrl(lkUrl)
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApi(@Named("Clean") retrofit: Retrofit): AuthApi{
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLkApi(@Named("WithCookie") retrofit: Retrofit): LkApi{
        return retrofit.create(LkApi::class.java)
    }
}