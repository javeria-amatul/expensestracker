package com.phoenix.expensetracker.feature_trans.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.phoenix.expensetracker.feature_trans.data.data_source.TransactionDatabase.Companion.DATABASE_VERSION
import com.phoenix.expensetracker.feature_trans.data.model.Transaction


@Database(
    entities = [Transaction::class],
    version = DATABASE_VERSION
)
abstract class TransactionDatabase: RoomDatabase() {

    abstract val transactionDao: TransactionDao

    companion object {
        const val DATABASE_NAME = "transaction_db"
        const val DATABASE_VERSION = 1
    }
}