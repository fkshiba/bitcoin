package com.felipeshiba.bitcoin.chart.data

import io.reactivex.Single

interface MarketPriceChartRepository {
    val chartValues: Single<Any>
}