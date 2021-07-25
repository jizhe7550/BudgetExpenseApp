package com.megatest.myapplication.framework.datasource.network.entity

class CurrencyEntity(
    val privacy: String,
    val quotes: Quotes,
    val source: String,
    val success: Boolean,
    val terms: String,
    val timestamp: Int,
)

data class Quotes(
    val USDUSD: Int,
    val USDNZD: Double,
)