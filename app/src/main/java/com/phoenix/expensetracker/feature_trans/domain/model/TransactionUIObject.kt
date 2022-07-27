package com.phoenix.expensetracker.feature_trans.domain.model

import com.phoenix.expensetracker.feature_trans.data.model.Transaction
import com.phoenix.expensetracker.feature_trans.util.TransactionType
import javax.inject.Inject

data class TransactionUIObject(
    val createAt: Long,
    val date: String,
    val desc: String,
    val type: TransactionType,
    val amount: Float
)

class TransactionMapper @Inject constructor() {
    fun transform(transaction: Transaction) : TransactionUIObject {
        return TransactionUIObject(transaction.createdAt, transaction.createdAtDate, transaction.desc,transaction.type, transaction.amount)
    }
}