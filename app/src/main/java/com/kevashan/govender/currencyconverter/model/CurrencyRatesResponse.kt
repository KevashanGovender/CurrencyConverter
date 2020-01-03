package com.kevashan.govender.currencyconverter.model

import java.io.Serializable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CurrencyRatesResponse : Serializable {

    @SerializedName("base")
    @Expose
    var base: String? = null
    @SerializedName("date")
    @Expose
    var date: String? = null
    @SerializedName("rates")
    @Expose
    var rates: Rates? = null

    companion object {
        private const val serialVersionUID = 7731095221720911660L
    }

}