package com.kevashan.govender.currencyconverter.repo

import com.kevashan.govender.currencyconverter.model.CurrencyRatesResponse
import com.kevashan.govender.currencyconverter.service.CurrencyService
import io.reactivex.Observable

open class GetCurrencyRatesRepo (private val service: CurrencyService) {

    fun getCurrencyRates(base: String) : Observable<CurrencyRatesResponse> {
        return service.getCurrencyRates(base)
    }
}