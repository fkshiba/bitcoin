package com.felipeshiba.core.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named

@Module
class NetworkModule {

    @Provides
    @Named("baseUrl")
    fun provideBaseUrl(): String {
        return "https://api.blockchain.info/"
    }

    @Provides
    fun provideRetrofit(@Named("baseUrl") baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .build()
    }
}