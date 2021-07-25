package com.megatest.myapplication.framework.presentation.detail

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.megatest.myapplication.R
import com.megatest.myapplication.databinding.FragmentTransactionDetailBinding
import com.megatest.myapplication.databinding.FragmentTransactionListBinding
import com.megatest.myapplication.framework.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionDetailFragment :
    BaseFragment<TransactionDetailViewModel, FragmentTransactionDetailBinding>(R.layout.fragment_transaction_detail) {

    override val viewModel: TransactionDetailViewModel by viewModels()

    override fun setupChannel() {
    }

    override fun subscribeObservers() {
    }

    override fun initBinding(view: View): FragmentTransactionDetailBinding =
        FragmentTransactionDetailBinding.bind(view)

    override fun init() {
        setupUI()
    }

    private fun setupUI() {
        binding.btnFinish.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.tvDate.setOnClickListener {
            findNavController().navigate(R.id.action_transactionDetailFragment_to_datePickerFragment)
        }

        binding.tvTime.setOnClickListener {
            findNavController().navigate(R.id.action_transactionDetailFragment_to_timePickerFragment)
        }

        binding.tvCategory.setOnClickListener {
            findNavController().navigate(R.id.action_transactionDetailFragment_to_categoryFragment)
        }
    }


}