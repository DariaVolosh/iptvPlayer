package com.example.iptvplayer.di

import com.example.iptvplayer.retrofit.IptvService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://epg.best/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    fun provideService(): IptvService = provideRetrofit().create(IptvService::class.java)
}