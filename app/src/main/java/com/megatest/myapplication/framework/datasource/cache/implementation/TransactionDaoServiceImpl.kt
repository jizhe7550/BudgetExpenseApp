package com.megatest.myapplication.framework.datasource.cache.implementation

import com.megatest.myapplication.business.domain.model.TransactionModel
import com.megatest.myapplication.framework.datasource.cache.abstraction.ITransactionDaoService
import com.megatest.myapplication.framework.datasource.cache.database.TransactionDao
import com.megatest.myapplication.framework.datasource.cache.mapper.CacheMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionDaoServiceImpl
@Inject
constructor(
    private val transactionDao: TransactionDao,
    private val cacheMapper: CacheMapper,
) : ITransactionDaoService {

    override suspend fun insertTransaction(transaction: TransactionModel): Long {
        return transactionDao.insertTransaction(cacheMapper.mapToEntity(transaction))
    }

    override suspend fun getAllTransactions(): List<TransactionModel> {
        return cacheMapper.entityListToTransactionModelList(transactionDao.getAllTransactions())
    }

    override suspend fun searchTransactionById(id: String): TransactionModel {
        return cacheMapper.mapFromEntity(transactionDao.searchTransactionById(id))
    }


}