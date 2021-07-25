package com.megatest.myapplication.business.domain.model

data class TransactionModel(
    val id: String,
    val category: String,
    val valueUSD: String,
    val valueNZD: String,
    val recordRate: Double,
    val recordTimestamp: Long
)