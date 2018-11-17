package com.felipeshiba.bitcoin.di

import android.app.Application
import com.felipeshiba.bitcoin.CustomApp
import com.felipeshiba.core.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityModule::class, NetworkModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun networkModule(module: NetworkModule): Builder

        fun build(): AppComponent
    }

    fun inject(app: CustomApp)
}