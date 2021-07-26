package com.megatest.myapplication.framework.presentation.detail

import androidx.lifecycle.SavedStateHandle
import com.megatest.myapplication.business.domain.model.RateModel
import com.megatest.myapplication.business.domain.state.DataState
import com.megatest.myapplication.business.domain.state.StateEvent
import com.megatest.myapplication.business.interactors.TransactionInteractors
import com.megatest.myapplication.framework.presentation.base.BaseViewModel
import com.megatest.myapplication.framework.presentation.detail.state.TransactionStateEvent.*
import com.megatest.myapplication.framework.presentation.detail.state.TransactionViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TransactionDetailViewModel @Inject internal constructor(
    savedStateHandle: SavedStateHandle,
    private val transactionInteractors: TransactionInteractors
) : BaseViewModel<TransactionViewState>() {

    init {
        setStateEvent(GetRateFromNetEvent)
    }

    override fun handleNewData(data: TransactionViewState) {
        data.let { viewState ->
            viewState.rateModel?.let { rateModel ->
                setRateModel(rateModel)
            }
        }
    }

    override fun setStateEvent(stateEvent: StateEvent) {

        val job: Flow<DataState<TransactionViewState>?> = when (stateEvent) {

            is InsertTransactionEvent -> {
                transactionInteractors.insertNewTransaction(
                    newTransaction = stateEvent.transaction,
                    stateEvent = stateEvent
                )
            }

            is GetRateFromNetEvent -> {
                transactionInteractors.getRateFromNet(
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


    fun setTransactionCategory(category: String) {
        val update = getCurrentViewStateOrNew()
        update.category = category
        setViewState(update)
    }

    fun setTransactionDate(year: Int, month: Int, day: Int) {
        val update = getCurrentViewStateOrNew()
        var calendar = update.calendar ?: Calendar.getInstance()
        calendar.apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
            set(Calendar.DAY_OF_MONTH, day)
        }
        update.calendar = calendar
        setViewState(update)
    }

    fun setTransactionTime(hourOfDay: Int, minute: Int) {
        val update = getCurrentViewStateOrNew()
        var calendar = update.calendar ?: Calendar.getInstance()
        calendar.apply {
            set(Calendar.HOUR_OF_DAY, hourOfDay)
            set(Calendar.MINUTE, minute)
        }
        update.calendar = calendar
        setViewState(update)
    }

    private fun setRateModel(rateModel: RateModel) {
        val update = getCurrentViewStateOrNew()
        update.rateModel = rateModel
        setViewState(update)
    }

    fun setValueUSD(usd: Double) {
        val update = getCurrentViewStateOrNew()
        update.valueUSD = usd
        update.rateModel?.let { rateModel ->
            val rate = rateModel.rateNZD
            update.valueNZD = usd * rate
        }
        setViewState(update)
    }

    fun setValueNZD(nzd: Double) {
        val update = getCurrentViewStateOrNew()
        update.valueNZD = nzd
        update.rateModel?.let { rateModel ->
            val rate = rateModel.rateNZD
            update.valueUSD = nzd / rate
        }
        setViewState(update)
    }

}