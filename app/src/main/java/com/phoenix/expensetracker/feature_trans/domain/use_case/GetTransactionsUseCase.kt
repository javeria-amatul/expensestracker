package com.phoenix.expensetracker.feature_trans.domain.use_case

import com.phoenix.expensetracker.feature_trans.domain.model.TransactionUIObject
import com.phoenix.expensetracker.feature_trans.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow

class GetTransactionsUseCase(private val transactionRepository: TransactionRepository) {

    suspend operator fun invoke(): Flow<HashMap<String, MutableList<TransactionUIObject>>> {
        return transactionRepository.getTransactions()
    }

}