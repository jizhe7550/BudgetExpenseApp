package com.megatest.myapplication.framework.presentation

import com.megatest.myapplication.business.domain.state.DialogInputCaptureCallback
import com.megatest.myapplication.business.domain.state.Response
import com.megatest.myapplication.business.domain.state.StateMessageCallback


interface UIController {

    fun displayProgressBar(isDisplayed: Boolean)

    fun hideSoftKeyboard()

    fun displayInputCaptureDialog(title: String, callback: DialogInputCaptureCallback)

    fun onResponseReceived(
        response: Response,
        stateMessageCallback: StateMessageCallback
    )

}


















