package com.megatest.myapplication.di

import com.megatest.myapplication.business.FakeDataFactory
import com.megatest.myapplication.business.data.cache.FakeCacheDataSourceImpl
import com.megatest.myapplication.business.data.cache.FakeDaoService
import com.megatest.myapplication.business.data.cache.FakeDatabase
import com.megatest.myapplication.business.data.cache.FakeTransactionDao
import com.megatest.myapplication.business.data.network.FakeApiDataSourceImpl
import com.megatest.myapplication.business.data.cache.abstraction.ITransactionCacheDataSource
import com.megatest.myapplication.business.data.network.FakeApiService
import com.megatest.myapplication.business.data.network.abstraction.ITransactionApiDataSource
import com.megatest.myapplication.framework.datasource.cache.database.TransactionDao
import com.megatest.myapplication.framework.datasource.cache.mapper.CacheMapper
import com.megatest.myapplication.framework.datasource.network.mappers.ApiMapper
import com.megatest.myapplication.util.isUnitTest

class DependencyContainer {

    private val apiMapper = ApiMapper()
    private val cacheMapper = CacheMapper()
    lateinit var apiDataSource: ITransactionApiDataSource
    lateinit var cacheDataSource: ITransactionCacheDataSource
    lateinit var fakeDataFactory: FakeDataFactory
    lateinit var fakeDatabase: FakeDatabase
    lateinit var fakeDao: TransactionDao
    lateinit var fakeDaoService: FakeDaoService


    init {
        isUnitTest = true // for Logger.kt
    }

    fun build() {
        this.javaClass.classLoader?.let { classLoader ->
            fakeDataFactory = FakeDataFactory(classLoader)
        }
        val fakeApiService = FakeApiService(
            fakeDataFactory.produceFakeCurrencyResponse(),
            mapper = apiMapper
        )
        apiDataSource = FakeApiDataSourceImpl(
            fakeApiService
        )
        fakeDatabase = FakeDatabase()
        fakeDao = FakeTransactionDao(fakeDatabase)
        fakeDaoService = FakeDaoService(
            fakeDao,
            cacheMapper
        )
        cacheDataSource = FakeCacheDataSourceImpl(
            fakeDaoService = fakeDaoService
        )
    }
}

















