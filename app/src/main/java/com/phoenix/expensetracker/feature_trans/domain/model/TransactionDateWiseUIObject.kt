package com.phoenix.expensetracker.feature_trans.domain.model

import com.phoenix.expensetracker.feature_trans.data.model.Transaction
import javax.inject.Inject

data class TransactionDateWiseUIObject(
    val date: String,
    val transactions: MutableList<TransactionUIObject>
)

class TransactionDateWiseMapper @Inject constructor(private val transactionMapper: TransactionMapper) {
    fun transform(transactions: List<Transaction>?): HashMap<String, MutableList<TransactionUIObject>> {
        val map = HashMap<String, MutableList<TransactionUIObject>>()
        transactions?.let { list ->
            list.forEach { transaction ->
                if (!map.containsKey(transaction.createdAtDate)) {
                    val listOfTransactionUIObject = mutableListOf<TransactionUIObject>()
                    listOfTransactionUIObject.add(transactionMapper.transform(transaction))
                    map[transaction.createdAtDate] = listOfTransactionUIObject
                } else {
                    map[transaction.createdAtDate]?.add(transactionMapper.transform(transaction))
                }
            }
        }
        return map
    }
}