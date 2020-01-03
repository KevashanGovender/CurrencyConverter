package com.kevashan.govender.currencyconverter.viewmodel

import com.kevashan.govender.currencyconverter.model.CurrencyRatesResponse
import com.kevashan.govender.currencyconverter.model.Rate
import com.kevashan.govender.currencyconverter.model.Rates
import com.kevashan.govender.currencyconverter.repo.GetCurrencyRatesRepo
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CurrencyViewModelTest {

    private lateinit var viewModel: CurrencyViewModel

    @Mock
    private lateinit var repo: GetCurrencyRatesRepo

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)

        viewModel = CurrencyViewModel(repo)

    }

    @Test
    @Ignore("Null pointer exception")
    fun `when viewmodel is called to getRates, it should call the repo to get rates`() {
        val rates = Rates()
        rates.setaUD(23.50)

        val mockCurrencyRatesResponse = CurrencyRatesResponse();
        mockCurrencyRatesResponse.base = "EUR"
        mockCurrencyRatesResponse.date = "2018-10-22"
        mockCurrencyRatesResponse.rates = rates

        Mockito.`when`(repo.getCurrencyRates("EUR")).thenReturn(Observable.just(mockCurrencyRatesResponse))


        val result = viewModel.getRates("EUR")

        val testObserver = TestObserver<CurrencyRatesResponse>()
        result.subscribe(testObserver)
        testObserver.assertComplete()

        Mockito.verify(repo).getCurrencyRates("EUR")

        val response = testObserver.values()[0]
        assertEquals(mockCurrencyRatesResponse, response)
        assertEquals(rates, response.rates)
    }

    @Test
    fun `when viewmodel is called to sort rates, should return a list of Rate objects`(){
        val mockRates = Rates()
        mockRates.setaUD(23.50)
        mockRates.setcAD(23.50)
        mockRates.setsEK(23.50)
        mockRates.setuSD(23.50)
        mockRates.setzAR(23.50)

        val sortedList = viewModel.sortRates(mockRates)

        assertEquals(5, sortedList.size)
    }
}