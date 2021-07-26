package com.megatest.myapplication.business.domain.model

import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
object TransactionFactory{

    fun createSingleModel(): TransactionModel {
        return TransactionModel(
            id = UUID.randomUUID().toString(),
            category = EnumCategory.MEAL.name,
            valueUSD = 100.00,
            valueNZD = 133.333,
            recordRate = 1.333,
            recordTimestamp = 1627043804640
        )
    }

    fun createSingleModel2(): TransactionModel {
        return TransactionModel(
            id = UUID.randomUUID().toString(),
            category = EnumCategory.ENTERTAINMENT.name,
            valueUSD = 200.00,
            valueNZD = 288.80,
            recordRate = 1.444,
            recordTimestamp = 1627043684924
        )
    }
}