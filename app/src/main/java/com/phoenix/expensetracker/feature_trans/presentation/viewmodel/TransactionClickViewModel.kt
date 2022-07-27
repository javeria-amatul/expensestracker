package com.phoenix.expensetracker.feature_trans.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransactionClickViewModel @Inject constructor(): ViewModel() {

    fun deleteTransaction(){
        Log.e("Click" ," transaction click")
    }
}