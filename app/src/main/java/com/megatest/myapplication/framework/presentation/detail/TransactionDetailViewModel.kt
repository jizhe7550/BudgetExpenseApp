package com.megatest.myapplication.framework.presentation.detail

import com.megatest.myapplication.business.domain.state.StateEvent
import com.megatest.myapplication.framework.presentation.base.BaseViewModel
import com.megatest.myapplication.framework.presentation.detail.state.TransactionViewState
import com.megatest.myapplication.framework.presentation.list.state.TransactionListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransactionDetailViewModel@Inject internal constructor(

): BaseViewModel<TransactionViewState>() {

    override fun handleNewData(data: TransactionViewState) {
    }

    override fun setStateEvent(stateEvent: StateEvent) {
    }

    override fun initNewViewState(): TransactionViewState {
        return TransactionViewState()
    }

}