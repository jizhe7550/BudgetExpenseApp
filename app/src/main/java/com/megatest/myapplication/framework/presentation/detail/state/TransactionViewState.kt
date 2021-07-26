package com.megatest.myapplication.framework.presentation.detail.state

import com.megatest.myapplication.business.domain.model.RateModel
import com.megatest.myapplication.business.domain.model.TransactionModel
import java.util.*

data class TransactionViewState(
    var transaction: TransactionModel? = null,
    var category: String? = null,
    var calendar: Calendar? = null,
    var rateModel: RateModel? = null,
    var valueUSD:Double?=null,
    var valueNZD:Double?=null
)























