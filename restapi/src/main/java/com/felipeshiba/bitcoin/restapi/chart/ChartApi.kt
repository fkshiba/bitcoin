package com.felipeshiba.bitcoin.restapi.chart

import io.reactivex.Single
import retrofit2.http.GET

interface ChartApi {

    @GET("charts/market-price?timespan=4weeks")
    fun fetchBitcoinData(): Single<MarketPriceChartResponse>
}