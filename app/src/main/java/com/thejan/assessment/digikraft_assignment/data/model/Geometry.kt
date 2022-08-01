package com.thejan.assessment.digikraft_assignment.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Geometry(
    @SerializedName("coordinates") val coordinates: List<Double>,
    @SerializedName("type") val type: String
) : Parcelable