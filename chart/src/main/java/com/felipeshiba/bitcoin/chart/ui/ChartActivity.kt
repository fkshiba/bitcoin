package com.felipeshiba.bitcoin.chart.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.felipeshiba.bitcoin.chart.R
import com.felipeshiba.bitcoin.chart.data.model.ChartInfo
import com.felipeshiba.bitcoin.chart.di.inject
import com.felipeshiba.bitcoin.chart.ui.model.ChartViewModel
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.LargeValueFormatter
import kotlinx.android.synthetic.main.activity_chart.*
import javax.inject.Inject


class ChartActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var chartViewModel: ChartViewModel

    private val chartDataObserver = Observer<ChartInfo> {
        it?.let(this::drawChart)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)
        inject()
        initChart()
    }

    private fun initChart() {
        chartViewModel = ViewModelProviders.of(this, viewModelFactory).get(ChartViewModel::class.java)
        chartViewModel.chartData.observe(this, chartDataObserver)
    }

    private fun drawChart(chartInfo: ChartInfo) {
        name.text = chartInfo.name
        description.text = chartInfo.description

        val entries = ArrayList<Entry>()

        for (marketPrice in chartInfo.values) {
            entries.add(Entry(marketPrice.x.toFloat(), marketPrice.y.toFloat()))
        }

        val dataSet = LineDataSet(entries, chartInfo.name)
        val lineData = LineData(dataSet)
        with(chart) {
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            axisRight.isEnabled = false

            axisLeft.valueFormatter = LargeValueFormatter()
            xAxis.valueFormatter = DateAxisValueFormatter()
            xAxis.setDrawGridLines(false)

            data = lineData
            invalidate()
        }
    }
}
