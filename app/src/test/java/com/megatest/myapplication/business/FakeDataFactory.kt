package com.megatest.myapplication.business

import com.google.gson.Gson
import com.megatest.myapplication.business.domain.model.TransactionModel
import com.megatest.myapplication.framework.datasource.network.entity.CurrencyEntity

class FakeDataFactory(
    private val testClassLoader: ClassLoader,
) {

    fun produceFakeCurrencyResponse(): CurrencyEntity {
        val jsonStr = getCurrencyFromFile("currency_response.json")
        return Gson().fromJson(jsonStr, CurrencyEntity::class.java)
    }

    fun produceFakeCurrencyResponseStr(): String {
        return getCurrencyFromFile("currency_response.json")
    }

    fun produceFakeTransactionModel(): TransactionModel {
        return TransactionModel(
            id = "b13bd7f6-6c82-4fbe-a1f0-a9ba2517e859",
            category = "SHOPPING",
            valueUSD = 12.0,
            valueNZD = 17.208,
            showingValueUSD = "12.00",
            showingValueNZD = "17.21",
            recordRate = 1.434,
            showingRate = "1.43",
            recordTimestamp = 1625563085350,
            date = "Jul 6 2021",
            time = "21:18"
        )
    }

    /**
     * mock data from api
     */
    private fun getCurrencyFromFile(fileName: String): String {
        return testClassLoader.getResource(fileName).readText()
    }
}