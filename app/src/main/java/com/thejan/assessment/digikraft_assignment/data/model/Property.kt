package com.thejan.assessment.digikraft_assignment.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Property(
    @SerializedName("free_racks") val freeRacks: Int,
    @SerializedName("bikes") val bikes: Int,
    @SerializedName("label") val label: String,
    @SerializedName("bike_racks") val bikeRacks: Int,
    @SerializedName("updated") val updated: String,
) : Parcelable
