package com.megatest.myapplication.framework.datasource.network.mappers

import com.megatest.myapplication.business.domain.model.RateModel
import com.megatest.myapplication.business.domain.model.TransactionModel
import com.megatest.myapplication.business.domain.util.EntityMapper
import com.megatest.myapplication.framework.datasource.cache.entity.TransactionCacheEntity
import com.megatest.myapplication.framework.datasource.network.entity.CurrencyEntity
import javax.inject.Inject

class ApiMapper
@Inject
constructor() : EntityMapper<CurrencyEntity, RateModel> {
    override fun mapFromEntity(entity: CurrencyEntity): RateModel {
        return RateModel(
            rateNZD = entity.quotes.USDNZD,
            timestamp = entity.timestamp
        )
    }

    override fun mapToEntity(domainModel: RateModel): CurrencyEntity {
        TODO("Not yet implemented")
    }

}