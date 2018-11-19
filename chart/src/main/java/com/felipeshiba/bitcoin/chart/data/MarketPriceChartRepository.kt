package com.felipeshiba.bitcoin.chart.data

import com.felipeshiba.bitcoin.chart.data.model.ChartInfo
import io.reactivex.Single

interface MarketPriceChartRepository {
    val chartValues: Single<ChartInfo>
}