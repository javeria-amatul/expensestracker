package com.phoenix.expensetracker.feature_trans.domain.use_case

import com.phoenix.expensetracker.feature_trans.data.repository.FakeTransactionRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test

class GetTransactionsUseCaseTest {

    private lateinit var getTransactionsUseCase: GetTransactionsUseCase
    private lateinit var fakeTransactionRepository: FakeTransactionRepository

    @Before
    fun setUp() {
        fakeTransactionRepository = FakeTransactionRepository()
        getTransactionsUseCase = GetTransactionsUseCase(fakeTransactionRepository)
    }

    @Test
    fun `test getTransactions`() = runBlocking {
        getTransactionsUseCase.invoke().collect()
    }
}