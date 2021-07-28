package com.megatest.myapplication.business.data.cache

import com.megatest.myapplication.business.domain.model.TransactionModel
import com.megatest.myapplication.framework.datasource.cache.abstraction.ITransactionDaoService
import com.megatest.myapplication.framework.datasource.cache.database.TransactionDao
import com.megatest.myapplication.framework.datasource.cache.mapper.CacheMapper

class FakeDaoService(
    private val fakeDao: TransactionDao,
    private val mapper: CacheMapper
) : ITransactionDaoService {

    override suspend fun insertTransaction(transaction: TransactionModel): Long {
        return fakeDao.insertTransaction(mapper.mapToEntity(transaction))
    }

    override suspend fun getAllTransactions(): List<TransactionModel> {
        return mapper.entityListToTransactionModelList(fakeDao.getAllTransactions())
    }

    override suspend fun searchTransactionById(id: String): TransactionModel? {
        return fakeDao.searchTransactionById(id)?.let { entity ->
            mapper.mapFromEntity(entity)
        }
    }
}