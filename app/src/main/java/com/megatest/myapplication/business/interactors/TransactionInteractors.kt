package com.megatest.myapplication.business.interactors

import com.megatest.myapplication.business.data.cache.CacheResponseHandler
import com.megatest.myapplication.business.data.cache.abstraction.ITransactionCacheDataSource
import com.megatest.myapplication.business.data.util.safeCacheCall
import com.megatest.myapplication.business.domain.model.TransactionModel
import com.megatest.myapplication.business.domain.state.*
import com.megatest.myapplication.framework.presentation.detail.state.TransactionViewState
import com.megatest.myapplication.framework.presentation.list.state.TransactionListViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*

class TransactionInteractors(
    private val transactionCacheDataSource: ITransactionCacheDataSource,
) {

    fun insertNewTransaction(
        id: String? = null,
        newTransaction: TransactionModel,
        stateEvent: StateEvent
    ): Flow<DataState<TransactionViewState>?> = flow {

        val cacheResult = safeCacheCall(Dispatchers.IO) {
            transactionCacheDataSource.insertTransaction(newTransaction)
        }

        val cacheResponse = object : CacheResponseHandler<TransactionViewState, Long>(
            response = cacheResult,
            stateEvent = stateEvent
        ) {
            override suspend fun handleSuccess(resultObj: Long): DataState<TransactionViewState>? {
                return if (resultObj > 0) {
                    val viewState =
                        TransactionViewState(
                            newTransaction = newTransaction
                        )
                    DataState.data(
                        response = Response(
                            message = INSERT_TRANSACTION_SUCCESS,
                            uiComponentType = UIComponentType.Toast,
                            messageType = MessageType.Success
                        ),
                        data = viewState,
                        stateEvent = stateEvent
                    )
                } else {
                    DataState.data(
                        response = Response(
                            message = INSERT_TRANSACTION_FAILED,
                            uiComponentType = UIComponentType.Toast,
                            messageType = MessageType.Error
                        ),
                        data = null,
                        stateEvent = stateEvent
                    )
                }
            }
        }.getResult()

        emit(cacheResponse)
    }

    companion object {
        val INSERT_TRANSACTION_SUCCESS = "Successfully inserted new transaction."
        val INSERT_TRANSACTION_FAILED = "Failed to insert new transaction."
    }
}