package com.phoenix.expensetracker.feature_trans.presentation.transaction

sealed class TransactionState {
    class AddTransaction(val success: Boolean) : TransactionState()
}