package com.felipeshiba.bitcoin.chart.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.felipeshiba.bitcoin.chart.R
import com.felipeshiba.bitcoin.chart.di.inject
import com.felipeshiba.bitcoin.chart.ui.model.ChartViewModel
import javax.inject.Inject

class ChartActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var chartViewModel: ChartViewModel

    private val chartDataObserver = Observer<Any> {
        Toast.makeText(this, "$it", Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)
        inject()

        chartViewModel = ViewModelProviders.of(this, viewModelFactory).get(ChartViewModel::class.java)

        chartViewModel.chartData.observe(this, chartDataObserver)
    }
}
