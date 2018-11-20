package com.felipeshiba.bitcoin.chart.domain

import com.felipeshiba.bitcoin.chart.data.MarketPriceChartRepository
import com.felipeshiba.bitcoin.chart.data.model.ChartInfo
import com.felipeshiba.bitcoin.chart.data.model.MarketPriceValue
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import org.junit.Before
import org.junit.Test

class FetchMarketPriceChartDataUseCaseTest {

    @MockK
    lateinit var fetchMarketPriceChartDataBloc: FetchMarketPriceChartDataBloc

    @MockK
    lateinit var marketPriceChartRepository: MarketPriceChartRepository

    lateinit var fetchMarketPriceChartDataUseCase: FetchMarketPriceChartDataUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        every { fetchMarketPriceChartDataBloc.fetchMarketPriceAction } returns BehaviorSubject.createDefault(0)

        fetchMarketPriceChartDataUseCase =
                FetchMarketPriceChartDataUseCase(fetchMarketPriceChartDataBloc, marketPriceChartRepository)
    }

    @Test
    fun getMarketPriceChartData_success() {
        val chartInfo = ChartInfo("", "", listOf(MarketPriceValue(1, 1.0)))
        every { marketPriceChartRepository.chartValues } returns Single.just(chartInfo)

        val observer = fetchMarketPriceChartDataUseCase.marketPriceChartData.test()
        observer.onComplete()

        observer.assertComplete()
            .assertNoErrors()
            .assertValueCount(1)
            .assertValue(chartInfo)

        verify(exactly = 1) {
            marketPriceChartRepository.chartValues
        }
    }
}