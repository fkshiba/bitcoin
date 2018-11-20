package com.felipeshiba.bitcoin.chart.data

import com.felipeshiba.bitcoin.chart.data.model.ChartInfo
import com.felipeshiba.bitcoin.chart.data.model.MarketPriceValue
import com.felipeshiba.bitcoin.restapi.chart.ChartApi
import com.felipeshiba.core.schedulers.RxSchedulers
import io.reactivex.Single
import javax.inject.Inject

class AppMarketPriceChartRepository @Inject constructor(chartApi: ChartApi, schedulers: RxSchedulers) :
    MarketPriceChartRepository {

    override val chartValues: Single<ChartInfo> = chartApi.fetchBitcoinData()
        .subscribeOn(schedulers.io())
        .map { marketPriceChart ->
            val values = marketPriceChart.values.map { value ->
                MarketPriceValue(value.x, value.y)
            }
            ChartInfo(marketPriceChart.name, marketPriceChart.description, values)
        }
}