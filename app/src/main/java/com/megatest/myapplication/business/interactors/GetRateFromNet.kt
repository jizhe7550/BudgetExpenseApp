package com.megatest.myapplication.business.interactors

import com.megatest.myapplication.business.data.network.ApiResponseHandler
import com.megatest.myapplication.business.data.network.abstraction.ITransactionApiDataSource
import com.megatest.myapplication.business.data.util.safeApiCall
import com.megatest.myapplication.business.domain.model.RateModel
import com.megatest.myapplication.business.domain.state.*
import com.megatest.myapplication.framework.presentation.detail.state.TransactionViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRateFromNet(
    private val transactionApiDataSource: ITransactionApiDataSource,
) {

    fun execute(
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
                    TransactionInteractors.GET_RATE_FROM_NET_SUCCESS
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
}