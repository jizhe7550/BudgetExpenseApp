package com.megatest.myapplication.business.data.cache.abstraction

import com.megatest.myapplication.business.domain.model.TransactionModel

interface ITransactionCacheDataSource {

    suspend fun insertTransaction(transaction: TransactionModel):Long
}