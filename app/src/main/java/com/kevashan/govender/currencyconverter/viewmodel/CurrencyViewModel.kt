package com.kevashan.govender.currencyconverter.viewmodel

import com.kevashan.govender.currencyconverter.model.CurrencyRatesResponse
import com.kevashan.govender.currencyconverter.model.Rate
import com.kevashan.govender.currencyconverter.model.Rates
import com.kevashan.govender.currencyconverter.repo.GetCurrencyRatesRepo
import io.reactivex.Observable
import java.util.Arrays.asList

class CurrencyViewModel (private val repo: GetCurrencyRatesRepo) {

    fun getRates(base: String) : Observable<CurrencyRatesResponse> {
        return  repo.getCurrencyRates(base)
    }

    fun sortRates(rates: Rates) : List<Rate> {
        var rateEUR = Rate("EUR", "Euro", 1.00)
        var rateAud = Rate("AUD", "Austrailian Dollar", rates.getaUD())
        var rateCad = Rate("CAD", "Canadien Dollar", rates.getcAD())
        var rateSek = Rate("SEK", "Swedish Krona", rates.getsEK())
        var rateUsd = Rate("USD", "US Dollar", rates.getuSD())


        return asList(rateEUR, rateAud, rateCad, rateSek, rateUsd)
    }
}
