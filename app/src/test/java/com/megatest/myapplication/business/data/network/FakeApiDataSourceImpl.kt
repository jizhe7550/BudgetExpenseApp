package com.megatest.myapplication.business.data.network

import com.megatest.myapplication.business.data.network.abstraction.ITransactionApiDataSource
import com.megatest.myapplication.business.domain.model.RateModel
import com.megatest.myapplication.framework.datasource.cache.abstraction.ITransactionDaoService
import com.megatest.myapplication.framework.datasource.network.abstraction.ITransactionApiService
import com.megatest.myapplication.framework.datasource.network.entity.CurrencyEntity
import com.megatest.myapplication.framework.datasource.network.mappers.ApiMapper


class FakeApiDataSourceImpl
constructor(
    private val fakeApiService: ITransactionApiService
) : ITransactionApiDataSource {

    override suspend fun getRateFromNet(): RateModel {
        return fakeApiService.getRateFromNet()
    }

}