package com.felipeshiba.bitcoin.chart.di

import com.felipeshiba.bitcoin.chart.data.AppMarketPriceChartRepository
import com.felipeshiba.bitcoin.chart.data.MarketPriceChartRepository
import com.felipeshiba.bitcoin.chart.domain.AppFetchMarketPriceChartDataBloc
import com.felipeshiba.bitcoin.chart.domain.FetchMarketPriceChartDataBloc
import com.felipeshiba.bitcoin.restapi.chart.ChartApi
import com.felipeshiba.core.di.NetworkModule
import com.felipeshiba.core.schedulers.RxSchedulers
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module(includes = [NetworkModule::class])
class ChartActivityModule {

    @Provides
    fun providesChartApi(retrofit: Retrofit): ChartApi {
        return retrofit.create(ChartApi::class.java)
    }

    @Provides
    fun providesMarketPriceChartRepository(chartApi: ChartApi, schedulers: RxSchedulers): MarketPriceChartRepository {
        return AppMarketPriceChartRepository(chartApi, schedulers)
    }

    @Provides
    fun providesFetchMarketPriceChartDataBloc(): FetchMarketPriceChartDataBloc {
        return AppFetchMarketPriceChartDataBloc()
    }
}