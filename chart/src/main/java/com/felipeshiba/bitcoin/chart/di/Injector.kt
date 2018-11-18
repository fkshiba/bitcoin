package com.felipeshiba.bitcoin.chart.di

import com.felipeshiba.bitcoin.chart.ui.ChartActivity

fun ChartActivity.inject() {
    DaggerChartComponent.builder()
        .activity(this)
        .build()
        .inject(this)
}