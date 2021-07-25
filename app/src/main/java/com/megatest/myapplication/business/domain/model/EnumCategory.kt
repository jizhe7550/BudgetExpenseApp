package com.megatest.myapplication.business.domain.model

enum class EnumCategory {
    SHOPPING, ENTERTAINMENT, STUDYING, MEAL;


    companion object {
        fun getCategoryNames(): Array<out String> {
            return values().map {
                it.name
            }.toTypedArray()
        }
    }
}