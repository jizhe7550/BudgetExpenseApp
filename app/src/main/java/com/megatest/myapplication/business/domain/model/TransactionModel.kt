package com.megatest.myapplication.business.domain.model

import com.megatest.myapplication.framework.presentation.util.dateStr
import com.megatest.myapplication.framework.presentation.util.timeStr
import com.megatest.myapplication.framework.presentation.util.toFix2
import java.util.*

data class TransactionModel(
    val id: String,
    val category: String,
    val valueUSD: Double,
    val valueNZD: Double,
    val showingValueUSD: String = valueUSD.toFix2(),
    val showingValueNZD: String = valueNZD.toFix2(),
    val recordRate: Double,
    val showingRate: String = recordRate.toFix2(),
    val recordTimestamp: Long,
    val date: String = recordTimestamp.dateStr(),
    val time: String = recordTimestamp.timeStr(),
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TransactionModel

        if (id != other.id) return false
        if (category != other.category) return false
        if (valueUSD != other.valueUSD) return false
        if (valueNZD != other.valueNZD) return false
        if (recordRate != other.recordRate) return false
        if (recordTimestamp != other.recordTimestamp) return false

        return true
    }
}