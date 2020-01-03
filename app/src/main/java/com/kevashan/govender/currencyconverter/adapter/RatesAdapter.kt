package com.kevashan.govender.currencyconverter.adapter

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kevashan.govender.currencyconverter.R
import com.kevashan.govender.currencyconverter.model.Rate
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.rate_row.view.*
import java.util.*

class RatesAdapter(
    private val rates: List<Rate>,
    private val context: Context
) : RecyclerView.Adapter<RatesAdapter.RatesViewHolder>() {

    private var baseAmount: Double = 1.00

    private var displayRates = makeDisplayRates()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatesViewHolder {
        return RatesViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.rate_row,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return rates.size
    }

    override fun onBindViewHolder(holder: RatesViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class RatesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(position: Int) = with(itemView) {
            currency_short_name.text = displayRates[position].currencyShortName
            currency_long_name.text = displayRates[position].currencyLongName
            currency_value.setText(String.format("%.2f", displayRates[position].currencyValue))

            itemView.setOnClickListener {
                Collections.swap(displayRates, position, 0)
                currency_value.requestFocus()
                currency_value.setSelection(currency_value.text.length)
                notifyDataSetChanged()
            }
        }
    }

    private fun getExchangeRate(
        rateAmount: Double,
        displayAmount: Double,
        currencyCode: String
    ): Double {
        if ("EUR" == currencyCode) {
            return rateAmount
        }
        return (rateAmount * displayAmount)
    }

    private fun makeDisplayRates(): List<Rate> {
        var rateEUR = Rate("EUR", "Euro", 1.00)
        var rateAud = Rate(
            "AUD",
            "Austrailian Dollar",
            getExchangeRate(rates[1].currencyValue, baseAmount, "AUD")
        )
        var rateCad = Rate(
            "CAD",
            "Canadien Dollar",
            getExchangeRate(rates[2].currencyValue, baseAmount, "CAD")
        )
        var rateSek =
            Rate("SEK", "Swedish Krona", getExchangeRate(rates[3].currencyValue, baseAmount, "SEK"))
        var rateUsd =
            Rate("USD", "US Dollar", getExchangeRate(rates[4].currencyValue, baseAmount, "USD"))

        return listOf(rateEUR, rateAud, rateCad, rateSek, rateUsd)
    }
}