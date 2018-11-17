package com.felipeshiba.bitcoin.restapi.chart

import io.reactivex.Single
import retrofit2.http.GET


interface ChartApi {

    @GET("charts/market-price")
    fun fetchBitcoinData(): Single<MarketPriceChartResponse>
}