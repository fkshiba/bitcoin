package com.felipeshiba.core.di

import com.felipeshiba.core.schedulers.AppSchedulers
import com.felipeshiba.core.schedulers.RxSchedulers
import dagger.Module
import dagger.Provides

@Module
class SchedulersModule {

    @Provides
    fun provideSchedulers(): RxSchedulers = AppSchedulers
}