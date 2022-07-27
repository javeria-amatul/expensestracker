package com.phoenix.expensetracker.feature_trans.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.phoenix.expensetracker.R
import com.phoenix.expensetracker.databinding.TransactionItemBinding
import com.phoenix.expensetracker.feature_trans.domain.model.TransactionUIObject


class TransactionAdapter(private val onClick: (TransactionUIObject) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var transactions = ArrayList<TransactionUIObject>()

    fun setData(list: List<TransactionUIObject>) {
        transactions.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TransactionViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.transaction_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TransactionViewHolder).onBind(transactions[position])
        holder.itemView.setOnClickListener {
            onClick.invoke(transactions[position])
        }
    }

    override fun getItemCount(): Int {
        return transactions.size
    }


    /**
     * view holder for transaction
     */
    private inner class TransactionViewHolder(private val binding: TransactionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(uiModel: TransactionUIObject) {
            binding.apply {
                binding.data  = uiModel
                executePendingBindings()
            }
        }
    }
}