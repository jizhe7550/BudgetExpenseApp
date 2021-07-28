package com.megatest.myapplication.business.interactors

import com.megatest.myapplication.business.data.cache.abstraction.ITransactionCacheDataSource
import com.megatest.myapplication.business.data.network.abstraction.ITransactionApiDataSource
import com.megatest.myapplication.business.domain.state.DataState
import com.megatest.myapplication.di.DependencyContainer
import com.megatest.myapplication.framework.presentation.detail.state.TransactionStateEvent
import com.megatest.myapplication.framework.presentation.detail.state.TransactionStateEvent.InsertTransactionEvent
import com.megatest.myapplication.framework.presentation.detail.state.TransactionViewState
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@InternalCoroutinesApi
class InsertOrUpdateTransactionToCacheTest {

    private lateinit var dependencyContainer: DependencyContainer

    // system in test
    private lateinit var interactor: InsertOrUpdateTransactionToCache
    private lateinit var cacheDataSource: ITransactionCacheDataSource

    @BeforeEach
    fun setup() {
        dependencyContainer = DependencyContainer()
        dependencyContainer.build()
        cacheDataSource = dependencyContainer.cacheDataSource
        // instantiate the system in test
        interactor = InsertOrUpdateTransactionToCache(
            transactionCacheDataSource = cacheDataSource
        )
    }

    @Test
    fun `insert transaction to cache success`() = runBlocking{
        val newTransaction = dependencyContainer.fakeDataFactory.produceFakeTransactionModel()

       interactor.execute(
           newTransaction,
            stateEvent = InsertTransactionEvent(newTransaction)
        )?.collect(object : FlowCollector<DataState<TransactionViewState>?> {
            override suspend fun emit(value: DataState<TransactionViewState>?) {
                // check api success
                assertEquals(
                    value?.stateMessage?.response?.message,
                    TransactionInteractors.INSERT_TRANSACTION_SUCCESS
                )
            }
        })


        val cacheTransactionThatWasInserted = cacheDataSource.searchTransactionById(newTransaction.id)
        assertTrue { cacheTransactionThatWasInserted == newTransaction }
    }

    // TODO
    fun `insert transaction to cache fail`(){

    }

    // TODO
    fun `update transaction to cache success`(){

    }

    // TODO
    fun `update transaction to cache fail`(){

    }
}