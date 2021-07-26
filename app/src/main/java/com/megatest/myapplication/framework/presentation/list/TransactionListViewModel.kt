package com.megatest.myapplication.framework.presentation.list

import com.megatest.myapplication.business.domain.model.TransactionModel
import com.megatest.myapplication.business.domain.state.DataState
import com.megatest.myapplication.business.domain.state.StateEvent
import com.megatest.myapplication.business.interactors.TransactionInteractors
import com.megatest.myapplication.framework.presentation.base.BaseViewModel
import com.megatest.myapplication.framework.presentation.list.state.TransactionListSateEvent
import com.megatest.myapplication.framework.presentation.list.state.TransactionListSateEvent.*
import com.megatest.myapplication.framework.presentation.list.state.TransactionListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TransactionListViewModel @Inject internal constructor(
    private val transactionInteractors:TransactionInteractors
) : BaseViewModel<TransactionListViewState>() {

    override fun handleNewData(data: TransactionListViewState) {
        data.let { viewState ->
            viewState.transactionList?.let { transactionList->
                setTransactionListData(transactionList)
            }
        }
    }

    override fun setStateEvent(stateEvent: StateEvent) {


        val job: Flow<DataState<TransactionListViewState>?> = when (stateEvent) {
            is GetAllTransactionsInCacheEvent -> {
                transactionInteractors.getAllTransactionsInCache(stateEvent)
            }
            else -> {
                emitInvalidStateEvent(stateEvent)
            }
        }
        launchJob(stateEvent, job)
    }

    override fun initNewViewState(): TransactionListViewState {
        return TransactionListViewState()
    }

    fun retrieveTransactionListInCache() {
        setStateEvent(GetAllTransactionsInCacheEvent)
    }

    private fun setTransactionListData(transactionList: ArrayList<TransactionModel>){
        val update = getCurrentViewStateOrNew()
        update.transactionList = transactionList
        setViewState(update)
    }

}