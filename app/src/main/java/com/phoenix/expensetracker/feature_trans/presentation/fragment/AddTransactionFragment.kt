package com.phoenix.expensetracker.feature_trans.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.phoenix.expensetracker.R
import com.phoenix.expensetracker.databinding.FragmentAddTransactionBinding
import com.phoenix.expensetracker.feature_trans.presentation.transaction.TransactionState
import com.phoenix.expensetracker.feature_trans.presentation.viewmodel.AddTransactionViewmodel
import com.phoenix.expensetracker.feature_trans.util.TransactionType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTransactionFragment: DialogFragment() {

    private val viewModel: AddTransactionViewmodel by viewModels()
    lateinit var binding: FragmentAddTransactionBinding

    companion object{
        const val TAG = "AddTransactionFragment"
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTransactionBinding.inflate(inflater, container, false)
        setupViewBinding()
        return binding.root
    }

    /**
     * init view binding
     */
    private fun setupViewBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeState()
    }

    private fun initView() {
        val width = resources.getDimensionPixelSize(R.dimen.transaction_dialog_width)
        val height = resources.getDimensionPixelSize(R.dimen.transaction_dialog_width)
        dialog?.window?.setLayout(width, height)
        setUpTypeDropdown()
    }

    private fun setUpTypeDropdown() {
        val items = listOf(TransactionType.CREDIT.name, TransactionType.DEBIT.name)
        val adapter = ArrayAdapter(requireContext(), R.layout.type_item, items)
        (binding.tilTransaction.editText as? AutoCompleteTextView)?.setAdapter(adapter)
    }

    private fun observeState() {
        viewModel.transactionState.observe(viewLifecycleOwner) { state ->
            when(state) {
                is TransactionState.AddTransaction -> addNote(state.success)
            }
        }
    }

    private fun addNote(success: Boolean) {
        if (success) {
            Toast.makeText(requireContext(), getString(R.string.transaction_added), Toast.LENGTH_LONG).show()
            dismissNow()
        }
        else
            Toast.makeText(requireContext(), getString(R.string.fields_required), Toast.LENGTH_LONG).show()
    }
}
