package com.phoenix.expensetracker.feature_trans.presentation.activity

import android.content.DialogInterface
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.phoenix.expensetracker.R
import com.phoenix.expensetracker.databinding.ActivityMainBinding
import com.phoenix.expensetracker.feature_trans.domain.model.TransactionUIObject
import com.phoenix.expensetracker.feature_trans.presentation.adapter.TransactionDateWiseAdapter
import com.phoenix.expensetracker.feature_trans.presentation.fragment.AddTransactionFragment
import com.phoenix.expensetracker.feature_trans.presentation.transaction.MainActivityState
import com.phoenix.expensetracker.feature_trans.presentation.viewmodel.MainViewModel
import com.phoenix.expensetracker.feature_trans.util.extension.getFormattedAmount
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var transactionDateWiseAdapter: TransactionDateWiseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setOnclickListener()
        initTransaction()
        viewModel.getTransactionDateWise()
        observeValues()
        observeTransactions()
    }

    private fun setOnclickListener() {
        binding.icAddTransaction.setOnClickListener {
            addTransactionFragment()
        }
    }

    private fun addTransactionFragment() {
        val frag = AddTransactionFragment()
        frag.show(supportFragmentManager, AddTransactionFragment.TAG)
    }

    private fun initTransaction() {
        val layoutManager = LinearLayoutManager(this)
        binding.rvTransactions.layoutManager = layoutManager
        transactionDateWiseAdapter = TransactionDateWiseAdapter(this) {
            showDeleteTransactionDialog(it)
        }
    }

    private fun observeValues() {
        viewModel.updateValues.observe(this) { state ->
            when (state) {
                is MainActivityState.UpdateValues -> {
                    binding.tvIncome.text = getFormattedAmount(this, state.totalIncome)
                    binding.tvExpenses.text = getFormattedAmount(this, state.totalExpense)
                    binding.tvBalance.text = getFormattedAmount(this, state.balance)
                    binding.incomeExpenseProgress.progress = state.percentageOfExpenseToIncome
                }
            }
        }
    }

    private fun observeTransactions() {
        viewModel.transactions.observe(this) { state ->
            when(state) {
                is MainActivityState.SetTransactionData -> {
                    binding.rvTransactions.adapter = transactionDateWiseAdapter
                    transactionDateWiseAdapter.setData(state.transactionsDateWise)
                }
            }
        }
    }

    private fun showDeleteTransactionDialog(transactionUIObject: TransactionUIObject) {
        val alertDialog = AlertDialog.Builder(this).create()
        alertDialog.setCancelable(false)
        alertDialog.setTitle(getString(R.string.delete_transaction))
        alertDialog.setMessage(getString(R.string.delete_transaction_warning, transactionUIObject.desc))
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.yes))
        { _, _ -> viewModel.deleteTransaction(transactionUIObject) }
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.no))
        { dialog, _ -> dialog.dismiss() }
        alertDialog.show()
    }
}