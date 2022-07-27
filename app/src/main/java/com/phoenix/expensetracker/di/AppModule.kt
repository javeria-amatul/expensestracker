package com.phoenix.expensetracker.di

import android.app.Application
import androidx.room.Room
import com.phoenix.expensetracker.feature_trans.data.data_source.TransactionDatabase
import com.phoenix.expensetracker.feature_trans.data.repository.TransactionRepositoryImpl
import com.phoenix.expensetracker.feature_trans.domain.model.TransactionDateWiseMapper
import com.phoenix.expensetracker.feature_trans.domain.repository.TransactionRepository
import com.phoenix.expensetracker.feature_trans.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideTransactionDatabase(app: Application): TransactionDatabase {
        return Room.databaseBuilder(
            app,
            TransactionDatabase::class.java,
            TransactionDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTransactionRepository(
        db: TransactionDatabase,
        transactionDateWiseMapper: TransactionDateWiseMapper
    ): TransactionRepository {
        return TransactionRepositoryImpl(
            db.transactionDao,
            transactionDateWiseMapper
        )
    }

    @Provides
    @Singleton
    fun provideAddTransaction(repository: TransactionRepository): AddTransactionUseCase {
        return AddTransactionUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetTransactionsDateWiseUseCase(repository: TransactionRepository): GetTransactionsUseCase {
        return GetTransactionsUseCase(repository)
    }


    @Provides
    @Singleton
    fun provideDeleteTransactionUseCase(repository: TransactionRepository): DeleteTransactionUseCase {
        return DeleteTransactionUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideTransactionUseCases(repository: TransactionRepository): TransactionUseCase {
        return TransactionUseCase(
            addTransactionUseCase = AddTransactionUseCase(repository),
            getTransactionsUseCase = GetTransactionsUseCase(repository),
            deleteTransactionUseCase = DeleteTransactionUseCase(repository)
        )
    }
}