package com.megatest.myapplication.framework.datasource.cache.abstraction

import com.megatest.myapplication.business.domain.model.TransactionModel

interface ITransactionDaoService {

    suspend fun insertTransaction(transaction: TransactionModel): Long

    suspend fun getAllTransactions(): List<TransactionModel>

    suspend fun searchTransactionById(id: String): TransactionModel
}