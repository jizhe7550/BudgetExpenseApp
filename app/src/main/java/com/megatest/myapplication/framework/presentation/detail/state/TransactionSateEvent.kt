package com.megatest.myapplication.framework.presentation.detail.state

import com.megatest.myapplication.business.domain.model.TransactionModel
import com.megatest.myapplication.business.domain.state.StateEvent

sealed class TransactionSateEvent: StateEvent {

    class InsertTransactionEvent(
        val transaction: TransactionModel
    ): TransactionSateEvent() {

        override fun errorInfo(): String {
            return "Error inserting new transaction."
        }

        override fun eventName(): String {
            return "InsertTransactionEvent"
        }

        override fun shouldDisplayProgressBar() = true
    }
}