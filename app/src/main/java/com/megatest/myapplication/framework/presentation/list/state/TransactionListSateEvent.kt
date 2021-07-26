package com.megatest.myapplication.framework.presentation.list.state

import com.megatest.myapplication.business.domain.state.StateEvent

sealed class TransactionListSateEvent: StateEvent {

    object GetAllTransactionsInCacheEvent: TransactionListSateEvent() {

        override fun errorInfo(): String {
            return "Error get all transactions in cache."
        }

        override fun eventName(): String {
            return "GetAllTransactionsInCacheEvent"
        }

        override fun shouldDisplayProgressBar() = true
    }
}