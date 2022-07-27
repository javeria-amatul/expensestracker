package com.phoenix.expensetracker.feature_trans.domain.use_case

import com.phoenix.expensetracker.feature_trans.data.model.Transaction
import com.phoenix.expensetracker.feature_trans.domain.repository.TransactionRepository
import com.phoenix.expensetracker.feature_trans.util.TransactionType

class AddTransactionUseCase(private val repository: TransactionRepository) {

    suspend operator fun invoke(transaction: Transaction) {
        repository.addTransaction(transaction)
    }
}