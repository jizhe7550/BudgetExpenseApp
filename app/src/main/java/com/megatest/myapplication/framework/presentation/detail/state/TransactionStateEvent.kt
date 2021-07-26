package com.megatest.myapplication.framework.presentation.detail.state

import com.megatest.myapplication.business.domain.model.TransactionModel
import com.megatest.myapplication.business.domain.state.StateEvent

sealed class TransactionStateEvent: StateEvent {

    class InsertTransactionEvent(
        val transaction: TransactionModel
    ): TransactionStateEvent() {

        override fun errorInfo(): String {
            return "Error inserting new transaction."
        }

        override fun eventName(): String {
            return "InsertTransactionEvent"
        }

        override fun shouldDisplayProgressBar() = true
    }

    object GetRateFromNetEvent: TransactionStateEvent() {

        override fun errorInfo(): String {
            return "Error get rate from net."
        }

        override fun eventName(): String {
            return "GetRateFromNetEvent"
        }

        override fun shouldDisplayProgressBar() = true
    }
}