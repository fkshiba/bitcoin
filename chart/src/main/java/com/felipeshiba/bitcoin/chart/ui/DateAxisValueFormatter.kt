package com.felipeshiba.bitcoin.chart.ui

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import java.text.SimpleDateFormat
import java.util.*

class DateAxisValueFormatter: IAxisValueFormatter {
    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
        val simpleDateFormat = SimpleDateFormat("MMM d", Locale.getDefault())
        val timestamp = value.toLong()
        val date = Date(timestamp * 1000)
        return simpleDateFormat.format(date)
    }
}