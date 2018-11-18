package com.felipeshiba.bitcoin.di

import android.app.Application
import com.felipeshiba.core.di.NetworkModule

fun inject(application: Application) {
    DaggerAppComponent.builder()
        .application(application)
        .networkModule(NetworkModule())
        .build()
        .inject(application)
}