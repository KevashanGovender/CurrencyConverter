package com.kevashan.govender.currencyconverter.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kevashan.govender.currencyconverter.R
import com.kevashan.govender.currencyconverter.adapter.RatesAdapter
import com.kevashan.govender.currencyconverter.model.CurrencyRatesResponse
import com.kevashan.govender.currencyconverter.repo.GetCurrencyRatesRepo
import com.kevashan.govender.currencyconverter.service.CurrencyService
import com.kevashan.govender.currencyconverter.service.RetrofitClient
import com.kevashan.govender.currencyconverter.viewmodel.CurrencyViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CurrencyViewModel

    private val disposables = CompositeDisposable()
    private lateinit var ratesAdapter: RatesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = RetrofitClient.client
        val service = client.create(CurrencyService::class.java)

        val repo = GetCurrencyRatesRepo(service)

        viewModel = CurrencyViewModel(repo)
        rates_rv.layoutManager = LinearLayoutManager(this)

        refresh_btn.setOnClickListener{
            updateData()
        }

    }


    override fun onStart() {
        super.onStart()

        updateData()
    }

    override fun onResume() {
        super.onResume()

        updateData()
    }

    private fun updateData() {
        viewModel.getRates("EUR")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe(this::handleResponse, this::handleError)
    }

    private fun handleResponse(response: CurrencyRatesResponse){
        val sortedRates = viewModel.sortRates(response.rates!!)

        ratesAdapter = RatesAdapter(sortedRates, this)

        rates_rv.adapter = ratesAdapter
    }

    private fun handleError(throwable: Throwable){
        Log.e("Main Activity", throwable.message)
        Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
    }
}
