package com.phoenix.expensetracker.feature_trans.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.phoenix.expensetracker.R
import com.phoenix.expensetracker.databinding.DateWiseTransactionViewBinding
import com.phoenix.expensetracker.feature_trans.domain.model.TransactionDateWiseUIObject
import com.phoenix.expensetracker.feature_trans.domain.model.TransactionUIObject
import com.phoenix.expensetracker.feature_trans.presentation.DividerItemDecorator


class TransactionDateWiseAdapter(private val context: Context,val onClick: (TransactionUIObject) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val transactions = HashMap<String, MutableList<TransactionUIObject>>()

    private val dates = arrayListOf<String>()

    fun setData(map: HashMap<String, MutableList<TransactionUIObject>>) {
        map.keys.forEach { date ->
            if (transactions.containsKey(date) && !transactions[date].isNullOrEmpty()) {
                val updatedTransactions = map[date]
                transactions[date]?.clear()
                updatedTransactions?.let {transactions[date]?.addAll(it)}
            } else {
                map[date]?.let { transactions.put(date, it) }
                dates.add(date)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TransactionDateWiseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.date_wise_transaction_view,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (position) {
            transactions.size - 1 -> {
                val params = holder.itemView.layoutParams as RecyclerView.LayoutParams
                params.bottomMargin = context.resources.getDimension(R.dimen.item_margin).toInt()
                holder.itemView.layoutParams = params
            }
            0 -> {
                val params = holder.itemView.layoutParams as RecyclerView.LayoutParams
                params.topMargin = context.resources.getDimension(R.dimen.item_margin).toInt()
                holder.itemView.layoutParams = params
            }
            else -> {
            }
        }
        val date = dates[position]
        val transaction = transactions[date]
        transaction?.let {
            (holder as TransactionDateWiseViewHolder).onBind(TransactionDateWiseUIObject(date, it))
        }
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    /**
     * view holder for root transaction with date
     */
    private inner class TransactionDateWiseViewHolder(private val binding: DateWiseTransactionViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(uiModel: TransactionDateWiseUIObject) {
            binding.apply {
                tvDateOrDesc.text = uiModel.date
                val adapter = TransactionAdapter(onClick)
                adapter.setData(uiModel.transactions)
                rvTransactions.adapter = adapter
                ContextCompat.getDrawable(context, R.drawable.ic_divider)?.let {
                    val divider = DividerItemDecorator(it)
                    rvTransactions.addItemDecoration(divider)
                }
            }
        }
    }
}