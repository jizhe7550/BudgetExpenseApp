package com.megatest.myapplication.business.interactors

import com.megatest.myapplication.business.FakeDataFactory
import com.megatest.myapplication.business.data.network.ApiResponseHandler
import com.megatest.myapplication.business.data.network.abstraction.ITransactionApiDataSource
import com.megatest.myapplication.business.data.network.implementation.TransactionApiDataSourceImpl
import com.megatest.myapplication.business.data.util.safeApiCall
import com.megatest.myapplication.business.domain.model.RateModel
import com.megatest.myapplication.business.domain.state.*
import com.megatest.myapplication.business.interactors.TransactionInteractors.Companion.GET_RATE_FROM_NET_SUCCESS
import com.megatest.myapplication.di.DependencyContainer
import com.megatest.myapplication.framework.datasource.network.abstraction.ITransactionApiService
import com.megatest.myapplication.framework.datasource.network.implementation.TransactionApiServiceImpl
import com.megatest.myapplication.framework.datasource.network.mappers.ApiMapper
import com.megatest.myapplication.framework.datasource.network.retrofit.ApiRetrofit
import com.megatest.myapplication.framework.presentation.detail.state.TransactionStateEvent
import com.megatest.myapplication.framework.presentation.detail.state.TransactionStateEvent.*
import com.megatest.myapplication.framework.presentation.detail.state.TransactionViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@InternalCoroutinesApi
class GetRateFromNetTest {

    private lateinit var dependencyContainer: DependencyContainer

    // system in test
    private lateinit var getRateFromNet: GetRateFromNet
    private lateinit var apiDataSource: ITransactionApiDataSource

    @BeforeEach
    fun setup() {
        dependencyContainer = DependencyContainer()
        dependencyContainer.build()
        apiDataSource = dependencyContainer.apiDataSource
        // instantiate the system in test
        getRateFromNet = GetRateFromNet(
            transactionApiDataSource = apiDataSource
        )
    }

    @Test
    fun `get rate from network success`() = runBlocking {
        val sourceModal = apiDataSource.getRateFromNet()
        getRateFromNet.execute(
            stateEvent = GetRateFromNetEvent
        )?.collect(object : FlowCollector<DataState<TransactionViewState>?> {
            override suspend fun emit(value: DataState<TransactionViewState>?) {
                // check api success
                assertEquals(
                    value?.stateMessage?.response?.message,
                    GET_RATE_FROM_NET_SUCCESS
                )
                val rateModel = value?.data?.rateModel

                // assert rate value and obj are same
                assertEquals(sourceModal.rateNZD,rateModel?.rateNZD)
                assertEquals(sourceModal,rateModel)
            }
        })
    }

    // TODO   `get rate from network failed`
}