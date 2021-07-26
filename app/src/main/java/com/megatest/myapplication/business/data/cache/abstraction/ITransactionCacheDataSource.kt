package com.megatest.myapplication.business.data.cache.abstraction

import com.megatest.myapplication.business.domain.model.TransactionModel

interface ITransactionCacheDataSource {

    suspend fun insertTransaction(transaction: TransactionModel): Long

    suspend fun getAllTransactions(): List<TransactionModel>

    suspend fun searchTransactionById(id:String):TransactionModel
}