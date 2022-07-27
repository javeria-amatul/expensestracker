package com.phoenix.expensetracker.feature_trans.presentation.transaction

import com.phoenix.expensetracker.feature_trans.domain.model.TransactionDateWiseUIObject
import com.phoenix.expensetracker.feature_trans.domain.model.TransactionUIObject
import kotlin.math.roundToInt


sealed class MainActivityState {
    class UpdateValues(
        val totalIncome: Float = 0f,
        val totalExpense: Float = 0f,
        val balance: Float = totalIncome - totalExpense,
        val percentageOfExpenseToIncome: Int = (totalExpense / totalIncome * 100).roundToInt()
    ) : MainActivityState()
    class SetTransactionData(val transactionsDateWise: HashMap<String, MutableList<TransactionUIObject>>) :
        MainActivityState()
}