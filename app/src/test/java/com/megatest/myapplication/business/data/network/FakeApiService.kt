package com.megatest.myapplication.business.data.network

import com.megatest.myapplication.business.domain.model.RateModel
import com.megatest.myapplication.framework.datasource.network.abstraction.ITransactionApiService
import com.megatest.myapplication.framework.datasource.network.entity.CurrencyEntity
import com.megatest.myapplication.framework.datasource.network.mappers.ApiMapper

class FakeApiService(
    private val currencyEntity: CurrencyEntity,
    private val mapper: ApiMapper
): ITransactionApiService {

    override suspend fun getRateFromNet(): RateModel {
        return mapper.mapFromEntity(currencyEntity)
    }
}