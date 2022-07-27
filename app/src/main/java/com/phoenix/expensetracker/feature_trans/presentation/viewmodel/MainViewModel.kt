package com.phoenix.expensetracker.feature_trans.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phoenix.expensetracker.feature_trans.domain.model.TransactionUIObject
import com.phoenix.expensetracker.feature_trans.domain.use_case.TransactionUseCase
import com.phoenix.expensetracker.feature_trans.presentation.transaction.MainActivityState
import com.phoenix.expensetracker.feature_trans.presentation.transaction.TransactionState
import com.phoenix.expensetracker.feature_trans.util.TransactionType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val transactionUseCase: TransactionUseCase) : ViewModel() {

    private val _updateValues = MutableLiveData<MainActivityState.UpdateValues>()
    val updateValues: LiveData<MainActivityState.UpdateValues> = _updateValues

    private val _transactions = MutableLiveData<MainActivityState.SetTransactionData>()
    val transactions: LiveData<MainActivityState.SetTransactionData> = _transactions

    fun getTransactionDateWise() {
        viewModelScope.launch {
            transactionUseCase.getTransactionsUseCase().collect { list ->
                list.let { transactionsWithDate ->
                    _transactions.postValue(
                        MainActivityState.SetTransactionData(
                            transactionsWithDate
                        )
                    )
                    getConsolidatedValues(transactionsWithDate)
                }
            }
        }
    }

    private fun getConsolidatedValues(transactionsWithDate: HashMap<String, MutableList<TransactionUIObject>>) {
        var totalIncome = 0f
        var totalExpense = 0f
        if (transactionsWithDate.isNotEmpty()) {
            transactionsWithDate.forEach {
                it.value.filter { transactionUIObject ->
                    transactionUIObject.type == TransactionType.CREDIT
                }.forEach {  transaction ->
                    totalIncome += transaction.amount
                }
                it.value.filter { transactionUIObject ->
                    transactionUIObject.type == TransactionType.DEBIT
                }.forEach{ transaction ->
                    totalExpense += transaction.amount
                }
            }
            _updateValues.postValue(
                MainActivityState.UpdateValues(
                    totalIncome,
                    totalExpense,
                    totalIncome - totalExpense
                )
            )
        }
    }

    fun deleteTransaction(transactionUIObject: TransactionUIObject) {
        viewModelScope.launch {
            transactionUseCase.deleteTransactionUseCase(transactionUIObject.createAt)
        }
    }
}

