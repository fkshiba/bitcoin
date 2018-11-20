package com.felipeshiba.bitcoin.chart.ui.model

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.felipeshiba.bitcoin.chart.data.model.ChartInfo
import com.felipeshiba.bitcoin.chart.data.model.MarketPriceValue
import com.felipeshiba.bitcoin.chart.domain.FetchMarketPriceChartDataUseCase
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.Flowable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class ChartViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @MockK
    lateinit var fetchMarketPriceChartDataUseCase: FetchMarketPriceChartDataUseCase

    private lateinit var chartViewModel: ChartViewModel

    private lateinit var chartInfo: ChartInfo

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        chartInfo = ChartInfo("", "", listOf(MarketPriceValue(1, 1.0)))

        every { fetchMarketPriceChartDataUseCase.marketPriceChartData } returns Flowable.just(chartInfo)

        chartViewModel = ChartViewModel(fetchMarketPriceChartDataUseCase)
    }

    @Test
    fun getChartData_success() {
        val observer = mockk<Observer<ChartViewModel.State?>>()
        val slot = slot<ChartViewModel.State>()
        every { observer.onChanged(capture(slot)) } just runs
        chartViewModel.stateLiveData.observeForever(observer)

        verify(ordering = Ordering.SEQUENCE) {
            observer.onChanged(ChartViewModel.State.Loading)
            observer.onChanged(ChartViewModel.State.Content(chartInfo))
            observer.onChanged(ChartViewModel.State.Finish)
        }
        chartViewModel.stateLiveData.removeObserver(observer)
    }
}