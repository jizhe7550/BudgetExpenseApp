package com.megatest.myapplication.framework.presentation.list

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.megatest.myapplication.R
import com.megatest.myapplication.business.domain.model.TransactionModel
import com.megatest.myapplication.business.domain.state.StateMessageCallback
import com.megatest.myapplication.databinding.FragmentTransactionListBinding
import com.megatest.myapplication.framework.presentation.adapter.TransactionListAdapter
import com.megatest.myapplication.framework.presentation.base.BaseFragment
import com.megatest.myapplication.framework.presentation.common.TopSpacingItemDecoration
import com.megatest.myapplication.framework.presentation.util.gone
import com.megatest.myapplication.framework.presentation.util.visible
import com.megatest.myapplication.util.cLog
import com.megatest.myapplication.util.cLogD
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionListFragment :
    BaseFragment<TransactionListViewModel, FragmentTransactionListBinding>(R.layout.fragment_transaction_list),
    TransactionListAdapter.Interaction {

    override val viewModel: TransactionListViewModel by viewModels()
    private var transactionListAdapter: TransactionListAdapter? = null

    override fun setupChannel() {
        viewModel.setupChannel()
    }

    override fun subscribeObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, { viewState ->
            viewState?.apply {
                transactionList?.let { transactionList->
                    if (transactionList.isEmpty()){
                        binding.rvTransitionList.gone()
                        binding.tvNoList.visible()
                    }else{
                        transactionListAdapter?.submitList(transactionList)
                        transactionListAdapter?.notifyDataSetChanged()
                        binding.rvTransitionList.visible()
                        binding.tvNoList.gone()
                    }
                }
            }
        })
        viewModel.shouldDisplayProgressBar.observe(viewLifecycleOwner, {
            uiController.displayProgressBar(it)
        })

        viewModel.stateMessage.observe(viewLifecycleOwner, { stateMessage ->
            stateMessage?.let {
                uiController.onResponseReceived(
                    response = stateMessage.response,
                    stateMessageCallback = object : StateMessageCallback {
                        override fun removeMessageFromStack() {
                            viewModel.clearStateMessage()
                        }
                    }
                )
            }
        })
    }

    override fun initBinding(view: View): FragmentTransactionListBinding =
        FragmentTransactionListBinding.bind(view)

    override fun init() {
        setupUI()
    }

    override fun onResume() {
        super.onResume()
        viewModel.retrieveTransactionListInCache()
    }

    private fun setupUI() {
        setupRecyclerView()

        binding.fabAdd.setOnClickListener {
            navigateToDetailFragment()
        }
    }

    private fun setupRecyclerView() {
        binding.rvTransitionList.apply {
            layoutManager = LinearLayoutManager(activity)
            val topSpacingDecorator = TopSpacingItemDecoration(20)
            addItemDecoration(topSpacingDecorator)

            transactionListAdapter = TransactionListAdapter(
                this@TransactionListFragment,
            )
            adapter = transactionListAdapter
        }
    }

    private fun navigateToDetailFragment() {
        findNavController().navigate(R.id.action_transactionListFragment_to_transactionDetailGraph)
    }

    override fun onItemSelected(position: Int, item: TransactionModel) {
        val transactionId = item.id
        val direction = TransactionListFragmentDirections.actionTransactionListFragmentToTransactionDetailGraph(
            transactionId
        )
        findNavController().navigate(direction)
   }

    override fun onDestroyView() {
        super.onDestroyView()
        transactionListAdapter = null
    }
}