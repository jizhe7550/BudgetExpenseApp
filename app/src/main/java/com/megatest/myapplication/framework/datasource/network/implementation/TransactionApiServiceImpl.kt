package com.megatest.myapplication.framework.datasource.network.implementation

import com.megatest.myapplication.business.domain.model.RateModel
import com.megatest.myapplication.framework.datasource.cache.mapper.CacheMapper
import com.megatest.myapplication.framework.datasource.network.abstraction.ITransactionApiService
import com.megatest.myapplication.framework.datasource.network.mappers.ApiMapper
import com.megatest.myapplication.framework.datasource.network.retrofit.ApiRetrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionApiServiceImpl
@Inject
constructor(
    private val apiRetrofit: ApiRetrofit,
    private val mapper: ApiMapper,
) : ITransactionApiService {

    override suspend fun getRateFromNet():RateModel {
        return mapper.mapFromEntity(apiRetrofit.getRateFromNet())
    }
}