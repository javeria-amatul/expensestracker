package com.phoenix.expensetracker.feature_trans.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.phoenix.expensetracker.feature_trans.data.model.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: Transaction)

    @Query("SELECT * FROM `transaction` ORDER BY createdAt ASC")
    fun getTransactions(): Flow<List<Transaction>?>

    @Query("DELETE FROM `transaction` WHERE createdAt = :createAt")
    suspend fun deleteTransaction(createAt: Long)

}