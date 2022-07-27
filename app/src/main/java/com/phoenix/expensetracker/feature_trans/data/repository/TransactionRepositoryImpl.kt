package com.phoenix.expensetracker.feature_trans.data.repository

import com.phoenix.expensetracker.feature_trans.data.data_source.TransactionDao
import com.phoenix.expensetracker.feature_trans.data.model.Transaction
import com.phoenix.expensetracker.feature_trans.domain.model.TransactionDateWiseMapper
import com.phoenix.expensetracker.feature_trans.domain.model.TransactionDateWiseUIObject
import com.phoenix.expensetracker.feature_trans.domain.model.TransactionUIObject
import com.phoenix.expensetracker.feature_trans.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TransactionRepositoryImpl(
    private val dao: TransactionDao,
    private val transactionDateWiseMapper: TransactionDateWiseMapper
) : TransactionRepository {

    override suspend fun addTransaction(transaction: Transaction) {
        dao.insertTransaction(transaction)
    }

    override suspend fun getTransactions(): Flow<HashMap<String, MutableList<TransactionUIObject>>> {
        return flow {
            dao.getTransactions().collect { transactions ->
                if (!transactions.isNullOrEmpty()) {
                    emit(transactionDateWiseMapper.transform(transactions))
                }
            }
        }
    }

    override suspend fun deleteTransaction(timeStamp: Long) {
        return dao.deleteTransaction(timeStamp)
    }
}
