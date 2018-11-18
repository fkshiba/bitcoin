package com.felipeshiba.bitcoin.chart.di

import com.felipeshiba.bitcoin.chart.ui.ChartActivity
import com.felipeshiba.core.di.ModuleScope
import dagger.BindsInstance
import dagger.Component

@ModuleScope
@Component(
    modules = [ChartActivityModule::class, ChartViewModelModule::class]
)
interface ChartComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun activity(activity: ChartActivity): Builder

        fun build(): ChartComponent
    }

    fun inject(activity: ChartActivity)
}