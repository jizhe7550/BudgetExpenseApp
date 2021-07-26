package com.megatest.myapplication.business.data.network.implementation

import com.megatest.myapplication.business.data.network.abstraction.ITransactionApiDataSource
import com.megatest.myapplication.business.domain.model.RateModel
import com.megatest.myapplication.framework.datasource.network.abstraction.ITransactionApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionApiDataSourceImpl
@Inject
constructor(
    private val transactionApiService: ITransactionApiService
): ITransactionApiDataSource {

    override suspend fun getRateFromNet(): RateModel {
        return transactionApiService.getRateFromNet()
    }

}