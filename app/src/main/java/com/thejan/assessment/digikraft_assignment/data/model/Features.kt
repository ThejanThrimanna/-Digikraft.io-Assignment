package com.thejan.assessment.digikraft_assignment.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Features(
    @SerializedName("id") val id: String,
    @SerializedName("type") val type: String,
    @SerializedName("properties") val properties: Property,
    @SerializedName("geometry") val geometry: Geometry,
) : Parcelable
