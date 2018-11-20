package com.felipeshiba.bitcoin.chart.ui

import com.github.mikephil.charting.components.AxisBase
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DateAxisValueFormatterTest {

    @MockK
    lateinit var axisBase: AxisBase

    private lateinit var dateAxisValueFormatter: DateAxisValueFormatter

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        dateAxisValueFormatter = DateAxisValueFormatter()
    }

    @Test
    fun getFormattedValue_success() {
        val formattedValue = dateAxisValueFormatter.getFormattedValue(1542622385f, axisBase)

        assertEquals("Nov 19", formattedValue)
    }
}