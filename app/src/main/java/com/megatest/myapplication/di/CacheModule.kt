package com.megatest.myapplication.di

import com.megatest.myapplication.business.data.cache.abstraction.ITransactionCacheDataSource
import com.megatest.myapplication.business.data.cache.implementation.TransactionCacheDataSourceImpl
import com.megatest.myapplication.business.interactors.TransactionInteractors
import com.megatest.myapplication.framework.datasource.cache.abstraction.ITransactionDaoService
import com.megatest.myapplication.framework.datasource.cache.database.TransactionDao
import com.megatest.myapplication.framework.datasource.cache.implementation.TransactionDaoServiceImpl
import com.megatest.myapplication.framework.datasource.cache.mapper.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideTransactionCacheDataSource(
        transactionDaoService: ITransactionDaoService,
    ): ITransactionCacheDataSource {
        return TransactionCacheDataSourceImpl(transactionDaoService)
    }

    @Singleton
    @Provides
    fun provideTransactionDaoService(
        transactionDao: TransactionDao,
        cacheMapper: CacheMapper
    ): ITransactionDaoService{
        return TransactionDaoServiceImpl(transactionDao,cacheMapper)
    }

}