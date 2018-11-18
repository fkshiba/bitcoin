package com.felipeshiba.bitcoin.chart.domain

import io.reactivex.subjects.BehaviorSubject

interface FetchMarketPriceChartDataBloc {
    val fetchMarketPriceAction: BehaviorSubject<Int>
}