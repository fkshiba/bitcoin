package com.felipeshiba.bitcoin.chart.domain

import com.felipeshiba.bitcoin.chart.data.MarketPriceChartRepository
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import javax.inject.Inject

class FetchMarketPriceChartDataUseCase @Inject constructor(
    fetchMarketPriceChartDataBloc: FetchMarketPriceChartDataBloc,
    private val marketPriceChartRepository: MarketPriceChartRepository
) {
    val marketPriceChartData: Flowable<Any> = fetchMarketPriceChartDataBloc.fetchMarketPriceAction
        .toFlowable(BackpressureStrategy.LATEST)
        .flatMapSingle {
            marketPriceChartRepository.chartValues
        }

}