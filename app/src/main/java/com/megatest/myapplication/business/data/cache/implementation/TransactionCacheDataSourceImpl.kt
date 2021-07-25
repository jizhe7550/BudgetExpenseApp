package com.megatest.myapplication.business.data.cache.implementation

import com.megatest.myapplication.business.data.cache.abstraction.ITransactionCacheDataSource
import com.megatest.myapplication.business.domain.model.TransactionModel
import com.megatest.myapplication.framework.datasource.cache.abstraction.ITransactionDaoService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionCacheDataSourceImpl
@Inject
constructor(
    private val transactionDaoService: ITransactionDaoService
):ITransactionCacheDataSource
{
    override suspend fun insertTransaction(transaction: TransactionModel): Long {
        return transactionDaoService.insertTransaction(transaction)
    }
}