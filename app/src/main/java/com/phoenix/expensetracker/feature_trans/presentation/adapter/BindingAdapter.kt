package com.phoenix.expensetracker.feature_trans.presentation.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.phoenix.expensetracker.feature_trans.domain.model.TransactionDateWiseUIObject
import com.phoenix.expensetracker.feature_trans.domain.model.TransactionUIObject

//@BindingAdapter(value = ["setTransactions"])
//fun RecyclerView.setTransaction(transactions: List<TransactionUIObject>?) {
//    transactions?.let {
//        val transactionAdapter = TransactionAdapter(onClick.invoke(uiModel))
//        transactionAdapter.setData(it)
//        adapter = transactionAdapter
//    }
//}

//@BindingAdapter(value = ["setTransactionsDateWise"])
//fun RecyclerView.setTransactionsDateWise(transactions: List<TransactionDateWiseUIObject>?) {
//    transactions?.let {
//        val transactionAdapter = TransactionDateWiseAdapter(context)
//        transactionAdapter.setData(it)
//        adapter = transactionAdapter
//    }
//}