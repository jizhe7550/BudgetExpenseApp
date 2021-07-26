package com.megatest.myapplication.business.interactors

import com.megatest.myapplication.business.data.cache.CacheResponseHandler
import com.megatest.myapplication.business.data.cache.abstraction.ITransactionCacheDataSource
import com.megatest.myapplication.business.data.network.ApiResponseHandler
import com.megatest.myapplication.business.data.network.abstraction.ITransactionApiDataSource
import com.megatest.myapplication.business.data.util.safeApiCall
import com.megatest.myapplication.business.data.util.safeCacheCall
import com.megatest.myapplication.business.domain.model.RateModel
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
    private val transactionApiDataSource: ITransactionApiDataSource,
) {

    fun insertNewTransaction(
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
                            transaction = newTransaction
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

    fun searchTransactionById(
        id: String,
        stateEvent: StateEvent
    ): Flow<DataState<TransactionViewState>?> = flow {

        val cacheResult = safeCacheCall(Dispatchers.IO) {
            transactionCacheDataSource.searchTransactionById(id)
        }

        val response =
            object : CacheResponseHandler<TransactionViewState, TransactionModel>(
                response = cacheResult,
                stateEvent = stateEvent
            ) {
                override suspend fun handleSuccess(resultObj: TransactionModel): DataState<TransactionViewState>? {
                    return DataState.data(
                        response = Response(
                            message = SEARCH_TRANSACTION_SUCCESS,
                            uiComponentType = UIComponentType.None,
                            messageType = MessageType.Success
                        ),
                        data = TransactionViewState(
                            transaction = resultObj
                        ),
                        stateEvent = stateEvent
                    )
                }
            }.getResult()

        emit(response)
    }

    fun getAllTransactionsInCache(
        stateEvent: StateEvent
    ): Flow<DataState<TransactionListViewState>?> = flow {

        val cacheResult = safeCacheCall(Dispatchers.IO) {
            transactionCacheDataSource.getAllTransactions()
        }

        val response =
            object : CacheResponseHandler<TransactionListViewState, List<TransactionModel>>(
                response = cacheResult,
                stateEvent = stateEvent
            ) {
                override suspend fun handleSuccess(resultObj: List<TransactionModel>): DataState<TransactionListViewState>? {
                    var message: String? =
                        SEARCH_ALL_TRANSACTIONS_SUCCESS
                    var uiComponentType: UIComponentType? = UIComponentType.None
                    if (resultObj.isEmpty()) {
                        message =
                            SEARCH_TRANSACTIONS_NO_MATCHING_RESULTS
                        uiComponentType = UIComponentType.Toast
                    }
                    return DataState.data(
                        response = Response(
                            message = message,
                            uiComponentType = uiComponentType as UIComponentType,
                            messageType = MessageType.Success
                        ),
                        data = TransactionListViewState(
                            transactionList = ArrayList(resultObj)
                        ),
                        stateEvent = stateEvent
                    )
                }
            }.getResult()

        emit(response)
    }

    fun getRateFromNet(
        stateEvent: StateEvent
    ): Flow<DataState<TransactionViewState>?> = flow {

        val apiResult = safeApiCall(Dispatchers.IO) {
            transactionApiDataSource.getRateFromNet()
        }

        val apiResponse = object : ApiResponseHandler<TransactionViewState, RateModel>(
            response = apiResult,
            stateEvent = stateEvent
        ) {
            override suspend fun handleSuccess(resultObj: RateModel): DataState<TransactionViewState>? {
                var message: String? =
                    GET_RATE_FROM_NET_SUCCESS
                var uiComponentType: UIComponentType? = UIComponentType.None
                return DataState.data(
                    response = Response(
                        message = message,
                        uiComponentType = uiComponentType as UIComponentType,
                        messageType = MessageType.Success
                    ),
                    data = TransactionViewState(
                        rateModel = resultObj
                    ),
                    stateEvent = stateEvent
                )
            }
        }.getResult()

        emit(apiResponse)

    }

    companion object {
        const val INSERT_TRANSACTION_SUCCESS = "Successfully inserted new transaction."
        const val INSERT_TRANSACTION_FAILED = "Failed to insert new transaction."
        const val SEARCH_ALL_TRANSACTIONS_SUCCESS = "Successfully search all transactions."
        const val SEARCH_TRANSACTIONS_NO_MATCHING_RESULTS =
            "There are no transactions that match that query."
        const val GET_RATE_FROM_NET_SUCCESS = "Successfully get rate from net."
        const val PLEASE_INPUT_ALL_THE_FILL = "Please input all the values"
        const val SEARCH_TRANSACTION_SUCCESS = "Successfully search transactions."
        const val SEARCH_TRANSACTION_FAILED = "Failed to search this transaction."
    }
}