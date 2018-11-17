package com.felipeshiba.bitcoin.restapi.chart

data class MarketPriceChartResponse(
    val description: String,
    val name: String,
    val period: String,
    val status: String,
    val unit: String,
    val values: List<ValueMarketPriceChartResponse>
)