package com.kevashan.govender.currencyconverter.repo

import com.kevashan.govender.currencyconverter.model.CurrencyRatesResponse
import com.kevashan.govender.currencyconverter.model.Rates
import com.kevashan.govender.currencyconverter.service.CurrencyService
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class GetCurrencyRatesRepoTest {

    @Mock
    private lateinit var service: CurrencyService

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `when repo is called, should call API to fetch currency rates`(){
        val rates = Rates()
        rates.setaUD(23.50)

        val mockCurrencyRatesResponse = CurrencyRatesResponse();
        mockCurrencyRatesResponse.base = "EUR"
        mockCurrencyRatesResponse.date = "2018-10-22"
        mockCurrencyRatesResponse.rates = rates

        Mockito.`when`(service.getCurrencyRates("EUR")).thenReturn(Observable.just(mockCurrencyRatesResponse))

        val repo = GetCurrencyRatesRepo(service)

        val result = repo.getCurrencyRates("EUR")

        val testObserver = TestObserver<CurrencyRatesResponse>()
        result.subscribe(testObserver)
        testObserver.assertComplete()

        verify(service).getCurrencyRates("EUR")

        val response = testObserver.values()[0]
        assertEquals(mockCurrencyRatesResponse, response)
        assertEquals(rates, response.rates)
    }
}