package com.megatest.myapplication.framework.presentation.detail

import androidx.lifecycle.SavedStateHandle
import com.megatest.myapplication.business.domain.model.RateModel
import com.megatest.myapplication.business.domain.model.TransactionModel
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

const val SAFE_ARG_TRANSACTION_ID_SAVED_STATE_KEY = "transactionId"

@HiltViewModel
class TransactionDetailViewModel @Inject internal constructor(
    private val savedStateHandle: SavedStateHandle,
    private val transactionInteractors: TransactionInteractors
) : BaseViewModel<TransactionViewState>() {

    init {
        setFirstStateEvent()
    }

    private fun setFirstStateEvent() {
        val transactionId: String? =
            savedStateHandle.get<String>(SAFE_ARG_TRANSACTION_ID_SAVED_STATE_KEY)
        if (transactionId.isNullOrEmpty()) {
            setStateEvent(GetRateFromNetEvent)
        } else {
            setStateEvent(SearchTransactionByIdEvent(transactionId))
        }
    }

    override fun handleNewData(data: TransactionViewState) {
        data.let { viewState ->
            viewState.rateModel?.let { rateModel ->
                setRateModel(rateModel)
            }
            viewState.transaction?.let {
                setTransaction(it)
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

            is SearchTransactionByIdEvent -> {
                transactionInteractors.searchTransactionById(
                    stateEvent.transactionId,
                    stateEvent
                )
            }

            is GetRateFromNetEvent -> {
                transactionInteractors.getRateFromNet(
                    stateEvent = stateEvent
                )
            }

            is CreateStateMessageEvent -> {
                emitStateMessageEvent(
                    stateMessage = stateEvent.stateMessage,
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

    fun setTransaction(transactionModel: TransactionModel) {
        val update = getCurrentViewStateOrNew()
        transactionModel.apply {
            update.calendar = Calendar.getInstance().apply {
                timeInMillis = recordTimestamp
            }
            update.rateModel = RateModel(recordRate)
            update.category = category
            update.valueUSD = valueUSD
            update.valueNZD= valueNZD
        }
        setViewState(update)
    }

    fun checkFinish(): Boolean {
        viewState.value?.apply {
            if (calendar == null ||
                category == null ||
                calendar == null ||
                rateModel == null ||
                valueUSD == null ||
                valueNZD == null
            )
                return false
        }
        return true
    }

    fun setInsertTransactionEvent() {
        viewState.value?.apply {
            setStateEvent(
                InsertTransactionEvent(
                    TransactionModel(
                        id = UUID.randomUUID().toString(),
                        category = category!!,
                        valueUSD = valueUSD!!,
                        valueNZD = valueNZD!!,
                        recordRate = rateModel?.rateNZD!!,
                        recordTimestamp = calendar?.timeInMillis!!
                    )
                )
            )
        }

    }
}