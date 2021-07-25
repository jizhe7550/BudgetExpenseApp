package com.megatest.myapplication.framework.presentation.list.state

import com.megatest.myapplication.business.domain.state.StateEvent

sealed class TransactionListSateEvent: StateEvent {

    object GetAllTransactionsEvent: TransactionListSateEvent() {

        override fun errorInfo(): String {
            return "Error get all transactions."
        }

        override fun eventName(): String {
            return "GetAllTransactionsEvent"
        }

        override fun shouldDisplayProgressBar() = true
    }
}