package com.felipeshiba.core.schedulers

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

object TestSchedulers: RxSchedulers {
    override fun io(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun ui(): Scheduler {
        return Schedulers.trampoline()
    }
}