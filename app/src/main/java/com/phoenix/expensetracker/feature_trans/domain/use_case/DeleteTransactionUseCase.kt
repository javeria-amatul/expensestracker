package com.phoenix.expensetracker.feature_trans.domain.use_case

import com.phoenix.expensetracker.feature_trans.domain.repository.TransactionRepository

class DeleteTransactionUseCase(private val transactionRepository: TransactionRepository) {

    suspend operator fun invoke(createAt: Long) {
        return transactionRepository.deleteTransaction(createAt)
    }
}