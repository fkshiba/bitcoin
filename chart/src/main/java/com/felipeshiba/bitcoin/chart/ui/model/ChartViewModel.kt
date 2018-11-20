package com.felipeshiba.bitcoin.chart.ui.model

import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.ViewModel
import com.felipeshiba.bitcoin.chart.data.model.ChartInfo
import com.felipeshiba.bitcoin.chart.domain.FetchMarketPriceChartDataUseCase
import io.reactivex.Notification
import javax.inject.Inject

class ChartViewModel @Inject constructor(fetchMarketPriceChartDataUseCase: FetchMarketPriceChartDataUseCase) :
    ViewModel() {

    sealed class State {
        object Loading : State()
        data class Content(val data: ChartInfo) : State()
        data class Error(val message: String) : State()
        object Empty : State()
        object Finish : State()
    }

    val stateLiveData = LiveDataReactiveStreams.fromPublisher(fetchMarketPriceChartDataUseCase.marketPriceChartData
        .materialize()
        .map(this::setState)
        .startWith(State.Loading)
    )

    private fun setState(notification: Notification<ChartInfo>) =
        when {
            notification.isOnComplete -> State.Finish
            notification.isOnNext ->
                notification.value?.let { data ->
                    State.Content(data)
                } ?: State.Empty
            else -> State.Error(notification.error?.localizedMessage ?: "error")
        }
}