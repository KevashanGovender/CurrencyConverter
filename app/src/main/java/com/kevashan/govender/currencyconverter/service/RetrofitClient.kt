package com.kevashan.govender.currencyconverter.service

import com.google.gson.GsonBuilder
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory


object RetrofitClient {

    private var INSTANCE: Retrofit? = null

    private val BASE_URL = "https://revolut.duckdns.org/"

    val client: Retrofit
        get() {
            if (INSTANCE == null) {

                val gson = GsonBuilder()
                    .setLenient()
                    .create()

                INSTANCE = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }
            return INSTANCE!!
        }
}