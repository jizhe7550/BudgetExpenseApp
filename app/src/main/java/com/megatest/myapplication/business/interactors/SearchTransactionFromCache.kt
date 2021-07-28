package com.megatest.myapplication.business.interactors

import com.megatest.myapplication.business.data.cache.CacheResponseHandler
import com.megatest.myapplication.business.data.cache.abstraction.ITransactionCacheDataSource
import com.megatest.myapplication.business.data.util.safeCacheCall
import com.megatest.myapplication.business.domain.model.TransactionModel
import com.megatest.myapplication.business.domain.state.*
import com.megatest.myapplication.framework.presentation.detail.state.TransactionViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchTransactionFromCache(
    private val transactionCacheDataSource: ITransactionCacheDataSource,
) {

    fun execute(
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
                            message = TransactionInteractors.SEARCH_TRANSACTION_SUCCESS,
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

}