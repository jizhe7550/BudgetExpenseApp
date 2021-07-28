package com.megatest.myapplication.di

import com.megatest.myapplication.business.data.cache.abstraction.ITransactionCacheDataSource
import com.megatest.myapplication.business.data.network.abstraction.ITransactionApiDataSource
import com.megatest.myapplication.business.interactors.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorsModule {

    @Singleton
    @Provides
    fun provideTransactionInteractors(
        transactionCacheDataSource: ITransactionCacheDataSource,
        transactionApiDataSource: ITransactionApiDataSource,
    ): TransactionInteractors {
        return TransactionInteractors(
            SearchTransactionFromCache(transactionCacheDataSource),
            InsertOrUpdateTransactionToCache(transactionCacheDataSource),
            GetRateFromNet(transactionApiDataSource),
            GetAllTransactionsFromCache(transactionCacheDataSource)
        )
    }
}