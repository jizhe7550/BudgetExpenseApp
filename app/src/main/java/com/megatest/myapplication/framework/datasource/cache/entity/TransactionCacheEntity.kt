package com.megatest.myapplication.framework.datasource.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionCacheEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val category: String,
    val valueUSD: String,
    val valueNZD: String,
    val recordRate: Double,
    val recordTimestamp: Long
)
