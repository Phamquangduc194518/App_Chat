package com.t3h.chatapp.service

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Logger {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private inner class LoggingInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val response = chain.proceed(request)
            val requestLog = request.method + " " + request.url
            val responseLog = response.code.toString() + " " + response.message
            return response
        }

    }

    val okhttp = OkHttpClient.Builder()
        .connectTimeout(3000, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .addInterceptor(LoggingInterceptor())
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.9:9090")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okhttp)
        .build()

    val apiService = retrofit.create(ApiService::class.java)

}