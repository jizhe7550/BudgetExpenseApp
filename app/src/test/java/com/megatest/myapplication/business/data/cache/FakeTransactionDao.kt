package com.megatest.myapplication.business.data.cache

import com.megatest.myapplication.framework.datasource.cache.database.TransactionDao
import com.megatest.myapplication.framework.datasource.cache.entity.TransactionCacheEntity

class FakeTransactionDao(
    private val db: FakeDatabase
) : TransactionDao {

    override suspend fun insertTransaction(transaction: TransactionCacheEntity): Long {
        db.transactionCacheEntityList.removeIf {
            it.id == transaction.id
        }
        db.transactionCacheEntityList.add(transaction)
        return 1 // always return success
    }

    override fun getAllTransactions(): List<TransactionCacheEntity> {
        return db.transactionCacheEntityList
    }

    override fun searchTransactionById(id: String): TransactionCacheEntity? {
        for (entity in db.transactionCacheEntityList) {
            if (entity.id == id) {
                return entity
            }
        }
        return null
    }
}