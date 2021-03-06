package com.megatest.myapplication.framework.presentation.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.megatest.myapplication.business.domain.model.TransactionModel
import com.megatest.myapplication.databinding.ListItemTransactionBinding
import com.megatest.myapplication.framework.presentation.adapter.TransactionListAdapter

class TransactionListViewHolder
constructor(
    private val mBinding: ListItemTransactionBinding,
    private val interaction: TransactionListAdapter.Interaction?
) : RecyclerView.ViewHolder(mBinding.root) {

    fun bind(transactionModel: TransactionModel) {
        mBinding.tvCategory.text = transactionModel.category
        mBinding.tvCurrencyValue.text = transactionModel.showingValueUSD + "/" + transactionModel.showingValueNZD
        mBinding.tvDate.text = transactionModel.date
        mBinding.tvTime.text = transactionModel.time
        mBinding.tvRecordRate.text = transactionModel.showingRate

        mBinding.root.setOnClickListener {
            interaction?.onItemSelected(position = bindingAdapterPosition,transactionModel)
        }
    }
}