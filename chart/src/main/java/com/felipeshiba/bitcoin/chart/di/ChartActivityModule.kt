package com.felipeshiba.bitcoin.chart.di

import com.felipeshiba.bitcoin.chart.data.AppMarketPriceChartRepository
import com.felipeshiba.bitcoin.chart.data.MarketPriceChartRepository
import com.felipeshiba.bitcoin.chart.domain.AppFetchMarketPriceChartDataBloc
import com.felipeshiba.bitcoin.chart.domain.FetchMarketPriceChartDataBloc
import com.felipeshiba.bitcoin.restapi.chart.ChartApi
import com.felipeshiba.core.di.NetworkModule
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module(includes = [NetworkModule::class])
class ChartActivityModule {

    @Provides
    @Named("chartApi")
    fun providesChartApi(@Named("retrofit") retrofit: Retrofit): ChartApi {
        return retrofit.create(ChartApi::class.java)
    }

    @Provides
    fun providesMarketPriceChartRepository(@Named("chartApi") chartApi: ChartApi): MarketPriceChartRepository {
        return AppMarketPriceChartRepository(chartApi)
    }

    @Provides
    fun providesFetchMarketPriceChartDataBloc(): FetchMarketPriceChartDataBloc {
        return AppFetchMarketPriceChartDataBloc()
    }
}