package com.felipeshiba.bitcoin.chart.domain

import io.reactivex.subjects.BehaviorSubject

class AppFetchMarketPriceChartDataBloc : FetchMarketPriceChartDataBloc {
    override val fetchMarketPriceAction: BehaviorSubject<Int> = BehaviorSubject.createDefault(0)
}