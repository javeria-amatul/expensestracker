package com.phoenix.expensetracker.feature_trans.domain.use_case

import com.phoenix.expensetracker.feature_trans.data.model.Transaction
import com.phoenix.expensetracker.feature_trans.data.repository.FakeTransactionRepository
import com.phoenix.expensetracker.feature_trans.util.TransactionType
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AddTransactionUseCaseTest {

    private lateinit var addTransactionUseCase: AddTransactionUseCase
    private lateinit var fakeTransactionRepository: FakeTransactionRepository

    @Before
    fun setup() {
        fakeTransactionRepository = FakeTransactionRepository()
        addTransactionUseCase = AddTransactionUseCase(fakeTransactionRepository)
    }

    @Test
    fun `test addTransaction`() = runBlocking {
        val transaction = Transaction(1658932411, desc = "Salary", amount = 10000f, type= TransactionType.CREDIT)
        addTransactionUseCase.invoke(transaction)
    }
}