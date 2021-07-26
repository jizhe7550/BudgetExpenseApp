package com.megatest.myapplication.business.data.network.abstraction

import com.megatest.myapplication.business.domain.model.RateModel

interface ITransactionApiDataSource {

    suspend fun getRateFromNet():RateModel
}