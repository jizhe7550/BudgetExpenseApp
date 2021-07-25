package com.megatest.myapplication.framework.presentation.list

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.megatest.myapplication.R
import com.megatest.myapplication.databinding.FragmentTransactionListBinding
import com.megatest.myapplication.framework.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionListFragment :
    BaseFragment<TransactionListViewModel, FragmentTransactionListBinding>(R.layout.fragment_transaction_list) {

    override val viewModel: TransactionListViewModel by viewModels()

    override fun setupChannel() {
        viewModel.setupChannel()
    }

    override fun subscribeObservers() {
    }

    override fun initBinding(view: View): FragmentTransactionListBinding =
        FragmentTransactionListBinding.bind(view)

    override fun init() {
        setUI()
    }

    private fun setUI() {
        binding.fabAdd.setOnClickListener {
            navigateToDetailFragment()
        }
    }

    private fun navigateToDetailFragment() {
        findNavController().navigate(R.id.action_transactionListFragment_to_transactionDetailFragment)
    }
}