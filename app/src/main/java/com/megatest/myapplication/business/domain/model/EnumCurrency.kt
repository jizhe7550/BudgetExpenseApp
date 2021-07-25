package com.megatest.myapplication.business.domain.model

enum class EnumCurrency {

    USD,NZD;


    companion object {
        fun getCurrencyNames(): Array<out String> {
            return EnumCurrency.values().map {
                it.name
            }.toTypedArray()
        }
    }
}