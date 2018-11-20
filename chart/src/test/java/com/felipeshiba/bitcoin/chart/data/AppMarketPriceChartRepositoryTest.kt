package com.felipeshiba.bitcoin.chart.data

import com.felipeshiba.bitcoin.chart.data.model.MarketPriceValue
import com.felipeshiba.bitcoin.restapi.chart.ChartApi
import com.felipeshiba.bitcoin.restapi.chart.MarketPriceChartResponse
import com.felipeshiba.bitcoin.restapi.chart.ValueMarketPriceChartResponse
import com.felipeshiba.core.schedulers.TestSchedulers
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class AppMarketPriceChartRepositoryTest {

    @MockK
    lateinit var chartApi: ChartApi

    private lateinit var marketPriceChartRepository: MarketPriceChartRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        val chartPlot = ValueMarketPriceChartResponse(1, 1.0)
        val marketPriceChartResponse = MarketPriceChartResponse("", "", "", "", "", listOf(chartPlot))
        every { chartApi.fetchBitcoinData() } returns Single.just(marketPriceChartResponse)

        marketPriceChartRepository = AppMarketPriceChartRepository(chartApi, TestSchedulers)
    }

    @Test
    fun getChartValues_success() {
        val observer = marketPriceChartRepository.chartValues.test()
        observer.assertComplete()
            .assertNoErrors()
            .assertValueCount(1)
            .assertValue { chartInfo ->
                chartInfo.values == listOf(MarketPriceValue(1, 1.0))
            }

        verify(exactly = 1) { chartApi.fetchBitcoinData() }
    }
}