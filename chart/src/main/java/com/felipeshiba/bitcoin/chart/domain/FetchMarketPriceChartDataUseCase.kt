package com.felipeshiba.bitcoin.chart.domain

import com.felipeshiba.bitcoin.chart.data.MarketPriceChartRepository
import com.felipeshiba.bitcoin.chart.data.model.ChartInfo
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import javax.inject.Inject

class FetchMarketPriceChartDataUseCase @Inject constructor(
    fetchMarketPriceChartDataBloc: FetchMarketPriceChartDataBloc,
    private val marketPriceChartRepository: MarketPriceChartRepository
) {
    val marketPriceChartData: Flowable<ChartInfo> = fetchMarketPriceChartDataBloc.fetchMarketPriceAction
        .toFlowable(BackpressureStrategy.LATEST)
        .flatMapSingle {
            marketPriceChartRepository.chartValues
        }
}