package com.phoenix.expensetracker.feature_trans.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phoenix.expensetracker.feature_trans.data.model.Transaction
import com.phoenix.expensetracker.feature_trans.domain.use_case.TransactionUseCase
import com.phoenix.expensetracker.feature_trans.presentation.transaction.TransactionState
import com.phoenix.expensetracker.feature_trans.util.TransactionType
import com.phoenix.expensetracker.feature_trans.util.extension.tripleNonNull
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewmodel @Inject constructor(
    private val transactionUseCase: TransactionUseCase
) : ViewModel() {

    var _type = MutableLiveData<String>()
    val type: LiveData<String> = _type

    val _desc = MutableLiveData<String>()
    val desc: LiveData<String> = _desc

    val _amount = MutableLiveData<String>()
    val amount: LiveData<String> = _amount

    private val _transactionState = MutableLiveData<TransactionState>()
    val transactionState: LiveData<TransactionState> = _transactionState

    fun addTransaction() {
        val transaction = tripleNonNull(type.value?.let { TransactionType.valueOf(it) }, desc.value, amount.value)
        //TODO: handle exceptions in a single place
        transaction?.let {
            viewModelScope.launch {
                transactionUseCase.addTransactionUseCase(
                    Transaction(
                        type = TransactionType.valueOf(it.first.name),
                        desc = it.second,
                        amount = it.third.toFloat()
                    )
                )
                _transactionState.postValue(TransactionState.AddTransaction(true))
            }
        } ?: _transactionState.postValue(TransactionState.AddTransaction(false))
    }

}
