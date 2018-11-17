package com.felipeshiba.bitcoin

import android.app.Activity
import android.app.Application
import android.util.Log
import com.facebook.stetho.Stetho
import com.felipeshiba.bitcoin.di.DaggerAppComponent
import com.felipeshiba.core.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber
import javax.inject.Inject

class CustomApp : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        initDagger()
        initTimber()
        handleRxErrors()
        initStetho()
    }

    private fun initDagger() {
        DaggerAppComponent.builder()
            .application(this)
            .networkModule(NetworkModule("https://api.blockchain.info/"))
            .build()
            .inject(this)
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun handleRxErrors() {
        RxJavaPlugins.setErrorHandler {
            Timber.e(Log.getStackTraceString(it))
            Timber.e(it.localizedMessage)
        }
    }

    private fun initStetho() {
        Stetho.initializeWithDefaults(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}