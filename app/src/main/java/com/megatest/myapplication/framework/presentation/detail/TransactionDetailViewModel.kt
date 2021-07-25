package com.megatest.myapplication.framework.presentation.detail

import androidx.lifecycle.SavedStateHandle
import com.megatest.myapplication.business.domain.state.DataState
import com.megatest.myapplication.business.domain.state.StateEvent
import com.megatest.myapplication.business.interactors.TransactionInteractors
import com.megatest.myapplication.framework.presentation.base.BaseViewModel
import com.megatest.myapplication.framework.presentation.detail.state.TransactionSateEvent
import com.megatest.myapplication.framework.presentation.detail.state.TransactionSateEvent.*
import com.megatest.myapplication.framework.presentation.detail.state.TransactionViewState
import com.megatest.myapplication.framework.presentation.list.state.TransactionListViewState
import com.megatest.myapplication.framework.presentation.util.SAFE_ARG_TRANSACTION_ID_SAVED_STATE_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TransactionDetailViewModel@Inject internal constructor(
    savedStateHandle: SavedStateHandle,
    private val transactionInteractors: TransactionInteractors
): BaseViewModel<TransactionViewState>() {

    override fun handleNewData(data: TransactionViewState) {

    }

    override fun setStateEvent(stateEvent: StateEvent) {

        val job: Flow<DataState<TransactionViewState>?> = when(stateEvent){

            is InsertTransactionEvent -> {
                transactionInteractors.insertNewTransaction(
                    newTransaction = stateEvent.transaction,
                    stateEvent = stateEvent
                )
            }
            else -> {
                emitInvalidStateEvent(stateEvent)
            }
        }
        launchJob(stateEvent, job)
    }

    override fun initNewViewState(): TransactionViewState {
        return TransactionViewState()
    }


    fun setTransactionCategory(){
        val update = getCurrentViewStateOrNew()
    }


}