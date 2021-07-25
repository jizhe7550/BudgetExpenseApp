package com.megatest.myapplication.business.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RateModel(
    val rateNZD: Double
): Parcelable
