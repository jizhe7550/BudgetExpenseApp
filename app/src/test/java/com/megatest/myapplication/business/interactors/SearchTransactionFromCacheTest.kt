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

class SearchTransactionFromCacheTest {
    // TODO
}