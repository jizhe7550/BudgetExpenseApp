package com.megatest.myapplication.framework.presentation.detail

import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.megatest.myapplication.R
import com.megatest.myapplication.business.domain.model.TransactionFactory
import com.megatest.myapplication.business.domain.state.DialogInputCaptureCallback
import com.megatest.myapplication.business.domain.state.StateMessageCallback
import com.megatest.myapplication.databinding.FragmentTransactionDetailBinding
import com.megatest.myapplication.framework.presentation.base.BaseFragment
import com.megatest.myapplication.framework.presentation.detail.state.TransactionStateEvent.*
import com.megatest.myapplication.framework.presentation.util.dateStr
import com.megatest.myapplication.framework.presentation.util.timeStr
import com.megatest.myapplication.framework.presentation.util.toFix2
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionDetailFragment :
    BaseFragment<TransactionDetailViewModel, FragmentTransactionDetailBinding>(R.layout.fragment_transaction_detail) {

    override val viewModel: TransactionDetailViewModel by navGraphViewModels(R.id.nav_graph_detail) {
        defaultViewModelProviderFactory
    }

    override fun setupChannel() {
    }

    override fun subscribeObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, { viewState ->
            viewState?.apply {
                category?.let {
                    binding.tvCategory.text = it
                }
                calendar?.let {
                    binding.tvDate.text = it.timeInMillis.dateStr()
                    binding.tvTime.text = it.timeInMillis.timeStr()
                }
                rateModel?.let {
                    binding.tvRate.text = it.rateNZD.toFix2()
                }
                valueNZD?.let {
                    binding.tvValueNZD.text = it.toFix2()
                }
                valueUSD?.let {
                    binding.tvValueUSD.text = it.toFix2()
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

    override fun initBinding(view: View): FragmentTransactionDetailBinding =
        FragmentTransactionDetailBinding.bind(view)

    override fun init() {
        setupUI()
    }

    private fun setupUI() {
        binding.btnFinish.setOnClickListener {
            viewModel.setStateEvent(
                InsertTransactionEvent(
                    transaction = TransactionFactory.createSingleModel()
                )
            )
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

        binding.tvValueUSD.setOnClickListener{
            uiController.displayInputCaptureDialog(
                getString(R.string.input_usd_value),
                object: DialogInputCaptureCallback {
                    override fun onTextCaptured(text: String) {
                        viewModel.setValueUSD(text.toDouble())
                    }
                }
            )
        }

        binding.tvValueNZD.setOnClickListener{
            uiController.displayInputCaptureDialog(
                getString(R.string.input_nzd_value),
                object: DialogInputCaptureCallback {
                    override fun onTextCaptured(text: String) {
                        viewModel.setValueNZD(text.toDouble())
                    }
                }
            )
        }
    }


}