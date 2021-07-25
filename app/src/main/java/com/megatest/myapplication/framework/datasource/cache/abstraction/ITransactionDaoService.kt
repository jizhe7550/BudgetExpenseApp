package com.megatest.myapplication.framework.datasource.cache.abstraction

import com.megatest.myapplication.business.domain.model.TransactionModel

interface ITransactionDaoService {

    suspend fun insertTransaction(transaction: TransactionModel): Long
}