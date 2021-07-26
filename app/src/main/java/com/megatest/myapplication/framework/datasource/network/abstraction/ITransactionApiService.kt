package com.megatest.myapplication.framework.datasource.network.abstraction

import com.megatest.myapplication.business.domain.model.RateModel

interface ITransactionApiService {

    suspend fun getRateFromNet(): RateModel
}