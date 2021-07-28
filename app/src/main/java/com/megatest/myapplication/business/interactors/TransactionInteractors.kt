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
     val searchTransactionFromCache: SearchTransactionFromCache,
     val insertOrUpdateTransactionToCache: InsertOrUpdateTransactionToCache,
     val getRateFromNet: GetRateFromNet,
     val getAllTransactionsFromCache: GetAllTransactionsFromCache
) {


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