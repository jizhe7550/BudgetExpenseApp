package com.megatest.myapplication.framework.datasource.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionCacheEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var date: Long = System.currentTimeMillis()
)
