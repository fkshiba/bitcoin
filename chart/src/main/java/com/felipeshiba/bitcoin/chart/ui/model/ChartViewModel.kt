package com.felipeshiba.bitcoin.chart.ui.model

import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.ViewModel
import com.felipeshiba.bitcoin.chart.domain.FetchMarketPriceChartDataUseCase
import javax.inject.Inject

class ChartViewModel @Inject constructor(fetchMarketPriceChartDataUseCase: FetchMarketPriceChartDataUseCase) :
    ViewModel() {

    val chartData = LiveDataReactiveStreams.fromPublisher(fetchMarketPriceChartDataUseCase.marketPriceChartData)
}