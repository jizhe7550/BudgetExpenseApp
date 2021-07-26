package com.megatest.myapplication.framework.datasource.cache.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.megatest.myapplication.framework.datasource.cache.entity.TransactionCacheEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTransactions(transactions: List<TransactionCacheEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction:TransactionCacheEntity):Long

    @Query("SELECT * FROM transactions ORDER BY recordTimestamp")
    fun getAllTransactions(): List<TransactionCacheEntity>

    @Query("SELECT * FROM transactions WHERE id = :id")
    fun getTransactionById(id: String): TransactionCacheEntity

}