package com.megatest.myapplication.framework.datasource.network.retrofit

import com.megatest.myapplication.BuildConfig
import com.megatest.myapplication.business.domain.model.EnumCurrency
import com.megatest.myapplication.framework.datasource.network.entity.CurrencyEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRetrofit {

    @GET("live")
    suspend fun getCurrency(
        @Query("currencies") currency: String = "${EnumCurrency.NZD.name},${EnumCurrency.USD.name}",
        @Query("access_key") access_key: String = BuildConfig.ACCESS_KEY,
    ): CurrencyEntity
}