package com.felipeshiba.bitcoin.chart.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
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

    private val stateObserver = Observer<ChartViewModel.State?> {
        it?.let(this::handleState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)
        inject()
        initViewModel()
    }

    private fun initViewModel() {
        chartViewModel = ViewModelProviders.of(this, viewModelFactory).get(ChartViewModel::class.java)
        chartViewModel.stateLiveData.observe(this, stateObserver)
    }

    private fun handleState(state: ChartViewModel.State) {
        when (state) {
            is ChartViewModel.State.Loading -> showLoading()
            is ChartViewModel.State.Content -> drawChart(state.data)
            is ChartViewModel.State.Error -> showError(state.message)
            is ChartViewModel.State.Empty -> showEmpty()
        }
    }

    private fun showLoading() {
        Toast.makeText(this, getString(R.string.loading_state), Toast.LENGTH_LONG).show()
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

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun showEmpty() {
        Toast.makeText(this, getString(R.string.empty_state), Toast.LENGTH_LONG).show()
    }
}
