package com.benyq.guowanandroid.net

import com.benyq.guowanandroid.WanAndroidApp
import com.benyq.guowanandroid.isJson
import com.orhanobut.logger.Logger
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.franmontiel.persistentcookiejar.ClearableCookieJar
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor


const val BASE_URL = "https://www.wanandroid.com"

// 读超时
const val READ_TIME_OUT = 20L

// 写超时
const val WRITE_TIME_OUT = 30L

// 连接超时
const val CONNECT_TIME_OUT = 5L

object RetrofitFactory {

    lateinit var wanAndroidApi: WanAndroidApi

    fun init() {

        val loggingInterceptor = HttpLoggingInterceptor { message ->
            if (message.isJson()) {
                Logger.json(message)
            } else {
                Logger.i(message)
            }
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(loggingInterceptor)
            .cookieJar(getCookieJar())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()

        wanAndroidApi = retrofit.create(WanAndroidApi::class.java)
    }

    private fun getCookieJar(): ClearableCookieJar {
        return PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(WanAndroidApp.getInstance()));
    }

}