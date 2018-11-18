package com.felipeshiba.bitcoin.chart.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.felipeshiba.bitcoin.chart.ui.model.ChartViewModel
import com.felipeshiba.bitcoin.di.ViewModelFactory
import com.felipeshiba.core.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ChartViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ChartViewModel::class)
    abstract fun bindChartViewModel(chartViewModel: ChartViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}