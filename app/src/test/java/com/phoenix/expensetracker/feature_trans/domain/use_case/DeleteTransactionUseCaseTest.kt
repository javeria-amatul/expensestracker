package com.phoenix.expensetracker.feature_trans.domain.use_case

import com.phoenix.expensetracker.feature_trans.data.repository.FakeTransactionRepository
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test

class DeleteTransactionUseCaseTest {

    private lateinit var deleteTransactionUseCase: DeleteTransactionUseCase
    private lateinit var fakeTransactionRepository: FakeTransactionRepository

    @Before
    fun setUp() {
        fakeTransactionRepository = FakeTransactionRepository()
        deleteTransactionUseCase = DeleteTransactionUseCase(fakeTransactionRepository)
    }

    @Test
    fun `test deleteTransactions`() = runBlocking {
        deleteTransactionUseCase.invoke(1658932411)
    }
}