package com.megatest.myapplication.framework.presentation.detail.state

import com.megatest.myapplication.business.domain.model.TransactionModel
import com.megatest.myapplication.business.domain.state.StateEvent
import com.megatest.myapplication.business.domain.state.StateMessage

sealed class TransactionStateEvent : StateEvent {

    class SearchTransactionByIdEvent(
        val transactionId: String
    ) : TransactionStateEvent() {

        override fun errorInfo(): String {
            return "Error search transaction by $transactionId"
        }

        override fun eventName(): String {
            return "SearchTransactionByIdEvent"
        }

        override fun shouldDisplayProgressBar() = true
    }

    class InsertTransactionEvent(
        val transaction: TransactionModel
    ) : TransactionStateEvent() {

        override fun errorInfo(): String {
            return "Error inserting new transaction."
        }

        override fun eventName(): String {
            return "InsertTransactionEvent"
        }

        override fun shouldDisplayProgressBar() = true
    }

    object GetRateFromNetEvent : TransactionStateEvent() {

        override fun errorInfo(): String {
            return "Error get rate from net."
        }

        override fun eventName(): String {
            return "GetRateFromNetEvent"
        }

        override fun shouldDisplayProgressBar() = true
    }

    class CreateStateMessageEvent(
        val stateMessage: StateMessage
    ) : TransactionStateEvent() {

        override fun errorInfo(): String {
            return "Error creating a new state message."
        }

        override fun eventName(): String {
            return "CreateStateMessageEvent"
        }

        override fun shouldDisplayProgressBar() = false
    }
}