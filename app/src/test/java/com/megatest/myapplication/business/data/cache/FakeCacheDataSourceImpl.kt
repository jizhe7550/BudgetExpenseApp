package com.megatest.myapplication.business.data.cache

import com.megatest.myapplication.business.data.cache.abstraction.ITransactionCacheDataSource
import com.megatest.myapplication.business.domain.model.TransactionModel
import com.megatest.myapplication.framework.datasource.cache.entity.TransactionCacheEntity
import com.megatest.myapplication.framework.datasource.cache.mapper.CacheMapper

class FakeCacheDataSourceImpl
constructor(
    private val fakeDaoService: FakeDaoService
) : ITransactionCacheDataSource {
    override suspend fun insertTransaction(transaction: TransactionModel): Long {
        return fakeDaoService.insertTransaction(transaction)
    }

    override suspend fun getAllTransactions(): List<TransactionModel> {
        return fakeDaoService.getAllTransactions()
    }

    override suspend fun searchTransactionById(id: String): TransactionModel? {
        return fakeDaoService.searchTransactionById(id)
    }

}