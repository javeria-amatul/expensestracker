package com.phoenix.expensetracker.feature_trans.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.phoenix.expensetracker.feature_trans.util.TransactionType
import com.phoenix.expensetracker.feature_trans.util.extension.toDateFormat


@Entity
data class Transaction(
    @PrimaryKey
    val createdAt: Long = System.currentTimeMillis(),
    val createdAtDate: String = createdAt.toDateFormat(),
    val desc: String,
    var amount: Float = 0.0f,
    val type: TransactionType
    )
