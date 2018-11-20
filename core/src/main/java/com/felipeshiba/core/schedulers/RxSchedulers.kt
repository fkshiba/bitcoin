package com.felipeshiba.core.schedulers

import io.reactivex.Scheduler

interface RxSchedulers {
    fun io(): Scheduler
    fun ui(): Scheduler
}