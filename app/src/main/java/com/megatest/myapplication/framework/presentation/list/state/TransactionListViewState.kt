package com.megatest.myapplication.framework.presentation.list.state

import com.megatest.myapplication.business.domain.model.TransactionModel

data class TransactionListViewState(
    var transactionList: ArrayList<TransactionModel>? = null,
    var newTransaction: TransactionModel? = null
)























