package com.felipeshiba.bitcoin

import android.app.Activity
import android.app.Application
import android.util.Log
import com.facebook.stetho.Stetho
import com.felipeshiba.bitcoin.di.inject
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.reactivex.plugins.RxJavaPlugins
import javax.inject.Inject

class CustomApp : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        initDagger()
        handleRxErrors()
        initStetho()
    }

    private fun initDagger() {
        inject(this)
    }

    private val tag = CustomApp::class.simpleName

    private fun handleRxErrors() {
        RxJavaPlugins.setErrorHandler {
            Log.e(tag, Log.getStackTraceString(it))
            Log.e(tag, it.localizedMessage)
        }
    }

    private fun initStetho() {
        Stetho.initializeWithDefaults(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}