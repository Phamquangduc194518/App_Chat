package com.t3h.chatapp.service

import com.t3h.chatapp.model.Account
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/api/getAllAccount")
    fun getAllAccount(): Call<List<Account>>

}