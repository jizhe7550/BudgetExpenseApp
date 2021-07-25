package com.megatest.myapplication.util

import android.util.Log
import com.megatest.myapplication.util.Constants.DEBUG

fun cLog(message: String?) {
    message?.let {
        if(!DEBUG){
            Log.d("MyApp", message)
        }
    }
}

fun String.cLogD() {
    Log.d("MyApp", this)
}
