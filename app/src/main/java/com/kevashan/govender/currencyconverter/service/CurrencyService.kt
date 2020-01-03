package com.kevashan.govender.currencyconverter.service

import com.kevashan.govender.currencyconverter.model.CurrencyRatesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CurrencyService {

    @GET("latest")
    fun getCurrencyRates(@Query("base") base: String): Observable<CurrencyRatesResponse>
}