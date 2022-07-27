package com.phoenix.expensetracker.feature_trans.domain.use_case

data class TransactionUseCase(
    val addTransactionUseCase: AddTransactionUseCase,
    val getTransactionsUseCase: GetTransactionsUseCase,
    val deleteTransactionUseCase: DeleteTransactionUseCase
)