package com.megatest.myapplication.business.domain.model

import com.megatest.myapplication.framework.presentation.util.dateStr
import com.megatest.myapplication.framework.presentation.util.timeStr
import java.util.*

data class TransactionModel(
    val id: String,
    val category: String,
    val valueUSD: String,
    val valueNZD: String,
    val recordRate: Double,
    val recordTimestamp: Long,
    val date: String = recordTimestamp.dateStr(),
    val time: String = recordTimestamp.timeStr(),
) {
}