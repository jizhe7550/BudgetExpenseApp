package com.megatest.myapplication.framework.presentation.list

import com.megatest.myapplication.business.domain.state.StateEvent
import com.megatest.myapplication.framework.presentation.base.BaseViewModel
import com.megatest.myapplication.framework.presentation.list.state.TransactionListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransactionListViewModel@Inject internal constructor(

): BaseViewModel<TransactionListViewState>() {

    override fun handleNewData(data: TransactionListViewState) {
    }

    override fun setStateEvent(stateEvent: StateEvent) {
    }

    override fun initNewViewState(): TransactionListViewState {
        return TransactionListViewState()
    }

}