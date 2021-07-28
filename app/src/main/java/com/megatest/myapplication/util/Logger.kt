package com.megatest.myapplication.util

import android.util.Log
import com.megatest.myapplication.util.Constants.DEBUG
import com.megatest.myapplication.util.Constants.TAG

var isUnitTest = false

fun cLog(message: String?) {
    if (DEBUG && !isUnitTest) {
        message?.let {
            Log.d(TAG, "$message")
        }
    } else {
        println("$message")
    }

}

fun String.cLogD() {
    Log.d(TAG, this)
}
