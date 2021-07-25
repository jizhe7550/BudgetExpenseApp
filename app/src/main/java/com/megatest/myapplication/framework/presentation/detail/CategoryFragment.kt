package com.megatest.myapplication.framework.presentation.detail

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.navigation.navGraphViewModels
import com.megatest.myapplication.R
import com.megatest.myapplication.business.domain.model.EnumCategory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : DialogFragment(), DialogInterface.OnClickListener {

    private val transactionDetailViewModel: TransactionDetailViewModel by navGraphViewModels(R.id.nav_graph_detail) {
        defaultViewModelProviderFactory
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(getString(R.string.dialog_tilte_pick_category))
                .setItems(EnumCategory.getCategoryNames(),this)
            builder.create()
        } ?: throw IllegalStateException(getString(R.string.exception_activity_cannot_be_null))
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {

    }
}