package com.megatest.myapplication.di

import com.megatest.myapplication.BuildConfig
import com.megatest.myapplication.framework.datasource.network.retrofit.ApiRetrofit
import com.megatest.myapplication.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = when (BuildConfig.DEBUG) {
                        true -> HttpLoggingInterceptor.Level.BODY
                        false -> HttpLoggingInterceptor.Level.NONE
                    }
                }
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideCurrencyRetrofit(retrofit: Retrofit.Builder): ApiRetrofit {
        return retrofit
            .build()
            .create(ApiRetrofit::class.java)
    }
}