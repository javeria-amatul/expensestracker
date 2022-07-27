package com.phoenix.expensetracker.feature_trans.data.repository

import androidx.core.content.res.TypedArrayUtils.getText
import com.phoenix.expensetracker.feature_trans.data.model.Transaction
import com.phoenix.expensetracker.feature_trans.domain.model.TransactionDateWiseMapper
import com.phoenix.expensetracker.feature_trans.domain.model.TransactionMapper
import com.phoenix.expensetracker.feature_trans.domain.model.TransactionUIObject
import com.phoenix.expensetracker.feature_trans.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock


class FakeTransactionRepository : TransactionRepository {

    private val transactions = mutableListOf<Transaction>()

    override suspend fun addTransaction(transaction: Transaction) {
        transactions.add(transaction)
    }

    override suspend fun getTransactions(): Flow<HashMap<String, MutableList<TransactionUIObject>>> {
        return flow {
            val transactionDateWiseMapper = mock(TransactionDateWiseMapper::class.java)
            emit(transactionDateWiseMapper.transform(transactions))
        }
    }

    override suspend fun deleteTransaction(timeStamp: Long) {
        transactions.forEach {
            if (timeStamp == it.createdAt)
                transactions.remove(it)
        }
    }
}