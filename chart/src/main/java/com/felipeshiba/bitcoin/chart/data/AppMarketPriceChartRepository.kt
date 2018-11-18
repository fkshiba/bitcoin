package com.felipeshiba.bitcoin.chart.data

import com.felipeshiba.bitcoin.chart.data.model.MarketPriceValue
import com.felipeshiba.bitcoin.restapi.chart.ChartApi
import io.reactivex.Single
import javax.inject.Inject

class AppMarketPriceChartRepository @Inject constructor(chartApi: ChartApi) :
    MarketPriceChartRepository {

    override val chartValues: Single<Any> = chartApi.fetchBitcoinData().map { marketPrice ->
        marketPrice.values.map { value ->
            MarketPriceValue(value.x, value.y)
        }
    }
}