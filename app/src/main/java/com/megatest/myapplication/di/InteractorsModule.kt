package com.megatest.myapplication.di

import com.megatest.myapplication.business.data.cache.abstraction.ITransactionCacheDataSource
import com.megatest.myapplication.business.interactors.TransactionInteractors
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
    ): TransactionInteractors {
        return TransactionInteractors(transactionCacheDataSource)
    }
}