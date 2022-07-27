package com.phoenix.expensetracker.feature_trans.domain.repository

import com.phoenix.expensetracker.feature_trans.data.model.Transaction
import com.phoenix.expensetracker.feature_trans.domain.model.TransactionDateWiseUIObject
import com.phoenix.expensetracker.feature_trans.domain.model.TransactionUIObject
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {

    suspend fun addTransaction(transaction: Transaction)
    suspend fun getTransactions(): Flow<HashMap<String, MutableList<TransactionUIObject>>>
    suspend fun deleteTransaction(timeStamp: Long)
}