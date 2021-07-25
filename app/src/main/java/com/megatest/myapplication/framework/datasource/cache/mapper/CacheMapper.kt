package com.megatest.myapplication.framework.datasource.cache.mapper

import com.megatest.myapplication.business.domain.model.TransactionModel
import com.megatest.myapplication.business.domain.util.EntityMapper
import com.megatest.myapplication.framework.datasource.cache.entity.TransactionCacheEntity
import javax.inject.Inject

/**
 * Maps TransactionModel to TransactionCacheEntity or TransactionCacheEntity to TransactionModel.
 */
class CacheMapper
@Inject
constructor(

) : EntityMapper<TransactionCacheEntity, TransactionModel> {

    fun entityListToTransactionModelList(entities: List<TransactionCacheEntity>): List<TransactionModel> {
        val list: ArrayList<TransactionModel> = ArrayList()
        for (entity in entities) {
            list.add(mapFromEntity(entity))
        }
        return list
    }

    fun transactionModelListToEntityList(TransactionModels: List<TransactionModel>): List<TransactionCacheEntity> {
        val entities: ArrayList<TransactionCacheEntity> = ArrayList()
        for (TransactionModel in TransactionModels) {
            entities.add(mapToEntity(TransactionModel))
        }
        return entities
    }

    override fun mapFromEntity(entity: TransactionCacheEntity): TransactionModel {
        return TransactionModel(
            id = entity.id,
            category = entity.category,
            valueUSD = entity.valueUSD,
            valueNZD = entity.valueNZD,
            recordRate = entity.recordRate,
            recordTimestamp = entity.recordTimestamp
        )
    }

    override fun mapToEntity(domainModel: TransactionModel): TransactionCacheEntity {
        return TransactionCacheEntity(
            id = domainModel.id,
            category = domainModel.category,
            valueUSD = domainModel.valueUSD,
            valueNZD = domainModel.valueNZD,
            recordRate = domainModel.recordRate,
            recordTimestamp = domainModel.recordTimestamp
        )
    }
}







